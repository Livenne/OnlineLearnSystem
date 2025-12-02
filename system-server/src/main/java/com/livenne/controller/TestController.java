package com.livenne.controller;

import com.livenne.ResponseEntity;
import com.livenne.annotation.*;
import com.livenne.common.model.User;
import com.livenne.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Slf4j
@Controller("/file")
public class TestController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/t")
    public ResponseEntity<?> te() throws SQLException {
        RuntimeException runtimeException = new RuntimeException();
        log.info(runtimeException.getMessage());
        log.debug(runtimeException.getMessage());
        log.error(runtimeException.getMessage());
        log.trace(runtimeException.getMessage());
        log.warn(runtimeException.getMessage());
        ResultSet resultSet = null;
        resultSet.next();
        System.out.println(1/0);
        return ResponseEntity.success();
    }

    @GetMapping("/t1")
    public ResponseEntity<?> test(){
        User user = userRepo.getUser(1L);
        System.out.println(user);
        return ResponseEntity.success(user);
    }
    @GetMapping("/t2")
    public ResponseEntity<?> test2(){
        List<User> user = userRepo.getUserList();
        System.out.println(user);
        return ResponseEntity.success(user);
    }

    @GetMapping("/t3")
    public ResponseEntity<?> test3(){
        String username = userRepo.getUsername(2L);
        System.out.println(username);
        return ResponseEntity.success(username);
    }

    public static Map<Object, Set<Integer>> redisVirtual = new HashMap<>();

    public final String FILE_PATH = "E:\\Code\\Java\\OnlineLearnSystem\\system-server\\src\\main\\resources\\file\\";

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody byte[] fileData,
                                    @RequestParm("fileName") String fileName,
                                    @RequestParm("chuckIndex") String chuckIndex) {
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
