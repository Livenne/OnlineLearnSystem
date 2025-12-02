import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.livenne.ResponseEntity;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;

public class TEst {

//    @Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\21332\\Downloads\\code-snapshot2.png");
        try {
            upload(file, 1024 * 1024);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ObjectMapper mapper = new ObjectMapper();

    private static void upload(File file, int chuckSize) throws IOException, InterruptedException {
        String fileName = file.getName();
        long fileSize = file.length();
        int totalChuck = (int) Math.ceil((double) fileSize / chuckSize);

        Set<Integer> uploadedSet = checkProgress(fileName);

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {

            byte[] buffer = new byte[chuckSize];

            for (int i = 0; i < totalChuck; i++) {
                if (uploadedSet.contains(i)) {
                    System.out.println("Skip chuck:" + i);
                    continue;
                }

                int byteRead = raf.read(buffer,0,chuckSize);
                if (byteRead == -1) break;

                uploadFile(fileName, i, byteRead == chuckSize ? buffer : Arrays.copyOf(buffer, byteRead));
//                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mergeFile(fileName);
        System.out.println("Upload file complete!");

    }

    private static Set<Integer> checkProgress(String fileName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/file/check/" + fileName))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ResponseEntity<Set<Integer>> res = mapper.readValue(response.body(), new TypeReference<>() {
        });
        return res.getData();
    }

    private static void uploadFile(String fileName, int chuckIndex, byte[] chunkData) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/file/upload?fileName="+fileName+"&chuckIndex="+chuckIndex))
                .POST(HttpRequest.BodyPublishers.ofByteArray(chunkData))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("分块 " + chuckIndex + " 上传，响应状态: " + response.statusCode());
        System.out.println(response.body());
    }

    private static void mergeFile(String fileName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/file/merge/" + fileName))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
