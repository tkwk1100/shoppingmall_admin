package com.shopping.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shopping.service.ProductService;
import com.shopping.vo.ProductImageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    @Autowired
    ProductService prod_service;

    @PostMapping("/upload")
    public Map<String, Object> postFileUpload(
        @RequestPart MultipartFile file
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        // 파일이 저장될 폴더의 위치 (프로젝트가 D드라이브에 있으면  D:/shopping/images)
        // 프로젝트가 C드라이브에 있으면 C:/shopping/images
        // 맥 사용자 (/Users/아이디/Desktop/shopping/images)
        Path folderLocation = Paths.get("/shopping/images");
        // StringUtils의 cleanPath 기능을 이용하여 파일의 경로를 가져온다
        // cleanPath는 사용자의 운영체제 환경이 각기 다를 수 있으므로 한가지 표현 방식으로 통일한다.
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // . 을 기준으로 문자열을 여러 문자열로 자른 후 문자열 배열에 저장한다.
        String[] splitFileName = fileName.split("\\.");
        // 자른 문자열 중 가장 마지막 배열요소를 가져와서 ext 변수에 저장한다.
        // ex) 21.06.11.ppt
        // split을 작동한 결과
        // splitFileName[0] = "21"
        // splitFileName[1] = "06"
        // splitFileName[2] = "11"
        // splitFileName[3] = "ppt"
        // splitFileName.length = 4
        // ext에는 splitFileName[3] 에 들어있는 ppt가 들어가게된다.
        String ext = splitFileName[splitFileName.length-1];
        // ext = ext.toLowerCase(); // ext에 들은 값을 전체 소문자로 만들기
        System.out.println(ext.equalsIgnoreCase("jpg"));
        // 허용항목 jpg, jpeg, png, gif
        // equalsIgnoreCase (대소문자 관계없이 비교) 같으면 true / 다르면 false
        // ext가 jpg가 아니고, jpeg가 아니면서, png도 아니고, gif도 아닌것
        // jpg, jpeg, png, gif 이외의 것
        if(
            !ext.equalsIgnoreCase("jpg") &&
            !ext.equalsIgnoreCase("jpeg") &&
            !ext.equalsIgnoreCase("png") &&
            !ext.equalsIgnoreCase("gif")
        ) {
            resultMap.put("status", false);
            resultMap.put("message", "jpg, jpeg, png, gif파일만 등록할 수 있습니다.");
            return resultMap;
        }

        // 전송받은 파일을 저장할 때의 이름
        String saveFileName = null;
        Calendar c = Calendar.getInstance();
        // 전송받은 파일의 이름은 전송받은 시간을 ms 단위로 바꾼 것으로 설정한다.
        String uriName = ""+c.getTimeInMillis();
        saveFileName = uriName+"."+ext;
        
        // 사용자의 운영체제 환경이 각기 다를 수 있으므로 한가지 표현 방식으로 통일한다.
        // String을 File객체에서 사용가능한 Path 형태로 변환
        Path target = folderLocation.resolve(saveFileName);
        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e) {
            resultMap.put("status", false);
            resultMap.put("message", e.getMessage());
            return resultMap;
        }
        // Integer pi_seq = 1;
        String image_uri = "prod"+uriName;

        ProductImageVO pimgVO = new ProductImageVO();
        pimgVO.setPimg_file_name(saveFileName);
        pimgVO.setPimg_size((int)file.getSize());
        // pimgVO.setPimg_pi_seq(seq);
        pimgVO.setPimg_uri(image_uri);
        prod_service.insertProductImage(pimgVO);

        resultMap.put("status", true);
        resultMap.put("message", "파일 업로드 완료");
        resultMap.put("image_uri", image_uri);
        return resultMap;
    }

    @GetMapping("/image/{uri}")
    public ResponseEntity<Resource> getImage(@PathVariable String uri, HttpServletRequest request) throws Exception {
        Path folderLocation = Paths.get("/shopping/images");

        String fileName = prod_service.selectProductImagePath(uri);
        if(fileName == null) {
            return null;
        }
        // 파일의 실제 경로 생성.
        Path filePath = folderLocation.resolve(fileName).normalize();
        Resource r = new UrlResource(filePath.toUri());

        String contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+r.getFilename()+"\"")
                .body(r);
    }
}
