package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Controller("/file")
public class TestController {

    public static Map<Object, Set<Integer>> redisVirtual = new HashMap<>();

    public final String FILE_PATH = "E:\\Code\\Java\\OnlineLearnSystem\\system-server\\src\\main\\resources\\file\\";

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody byte[] fileData,
                                    @RequestParm("fileName") String fileName,
                                    @RequestParm("chuckIndex") String chuckIndex) { // 修改点1：直接接收字节数组和参数
        System.out.println("Upload File:" + fileName + "|" + chuckIndex + "| Data size: " + (fileData != null ? fileData.length : 0));

        File chuckDir = new File(FILE_PATH + fileName + "_chucks");
        if (!chuckDir.exists()) {
            chuckDir.mkdirs();
        }

        // 修改点2：手动将字节数组写入文件
        File temp = new File(chuckDir, "chuck-" + chuckIndex);
        try (FileOutputStream fos = new FileOutputStream(temp)) {
            fos.write(fileData); // 将接收到的字节数据写入文件
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.failureMsg("文件块写入失败");
        }

        Set<Integer> set = redisVirtual.getOrDefault(fileName, new HashSet<>());
        set.add(Integer.valueOf(chuckIndex));
        redisVirtual.put(fileName, set); // 记得更新Map

        return ResponseEntity.success();
    }

    @GetMapping("/merge/{fileName}")
    public ResponseEntity<?> merge(@PathVariable("fileName") String fileName){

        File chuckDir = new File(FILE_PATH + fileName + "_chucks");
        File[] chucks = chuckDir.listFiles();

        Arrays.sort(chucks,(f1,f2) -> {
            int a = f1.getName().charAt(f1.getName().length()-1);
            int b = f2.getName().charAt(f2.getName().length()-1);
            return a - b;
        });

        File output = new File(FILE_PATH + fileName);
        try(FileOutputStream fos = new FileOutputStream(output)){
            for(File chuck : chucks){
                Files.copy(chuck.toPath(),fos);
                chuck.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        chuckDir.delete();

        return ResponseEntity.success();
    }

    @GetMapping("/check/{fileName}")
    public ResponseEntity<?> check(@PathVariable("fileName") String fileName) {

        return ResponseEntity.success(redisVirtual.getOrDefault(fileName, new HashSet<>()));
    }
}
