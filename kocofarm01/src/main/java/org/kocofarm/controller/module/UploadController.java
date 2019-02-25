package org.kocofarm.controller.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.kocofarm.domain.comm.AttachFileVO;
import org.kocofarm.service.module.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	@Setter(onMethod_ = @Autowired) 
	private FileService service;
/*	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax");

	}// get mapping
*/
	@GetMapping("/uploadForm")
	public String uploadForm() {
		return "/module/fileRoom/uploadForm";

	}
	
	
	private String GetFolder(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		return str.replace("-", File.separator);
		//return str.replace("-", "\\");
	}
	
	private boolean checkImageType(File file){
		
		try {
			String contentType = Files.probeContentType(file.toPath());
			
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileVO>>
	uploadAjaxPost(MultipartFile[] uploadFile, Model model) {
		
		List<AttachFileVO> list = new ArrayList<>();

		String uploadFolder = "C:\\Users\\KOSTA\\git\\KocoFarmProject\\kocofarm01\\src\\main\\webapp\\resources\\upload";
		String uploadFolderPath = GetFolder();					
		
		//make folder---------------------------------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
			
		}
		// make yyyy/MM/dd folder
		
		for (MultipartFile multipartFile : uploadFile) {

			AttachFileVO attachFileVO = new AttachFileVO();
			
			String uploadFileName = multipartFile.getOriginalFilename();


			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			attachFileVO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			
			uploadFileName = uuid.toString() + "_"+ uploadFileName;
			
			
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				
				attachFileVO.setUploadPath(uploadFolderPath/*.replace("\\", "/")*/);// 수정하였음
				attachFileVO.setUuid(uuid.toString());
				
				
				// chec image file
				if(checkImageType(saveFile)) {
					
					
					attachFileVO.setFileType(true);
					
					FileOutputStream thumnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumnail, 300, 300);
					
					thumnail.close();
				}
				service.setFile(attachFileVO);
			
				//add to list
				list.add(attachFileVO);
				
				
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch

		} // for end
		return new ResponseEntity<>(list, HttpStatus.OK);
	}// ajaxPost end
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		
		File file = new File("C:\\Users\\KOSTA\\git\\KocoFarmProject\\kocofarm01\\src\\main\\webapp\\resources\\upload\\"+ fileName);
		
		
		ResponseEntity<byte[]> result = null;
			
		try {
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName) {
		
		
		Resource resource = new FileSystemResource("C:\\Users\\KOSTA\\git\\KocoFarmProject\\kocofarm01\\src\\main\\webapp\\resources\\upload\\"+ fileName);
		
		
		if(resource.exists() == false){
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		
		String resourceName = resource.getFilename();
		
		//UUID remove
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") +1);
		
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			
			String downloadName = null;
			
			if(userAgent.contains("Trident")){
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+"," ");
			}else if(userAgent.contains("Edge")) {
				downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
				
			}else{ 
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
				
			}
			
			
			headers.add("Content-Disposition","attachment; filename=" + downloadName);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping(value="/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		
		
		File file;
		
		
		try {
			file = new File("C:\\Users\\KOSTA\\git\\KocoFarmProject\\kocofarm01\\src\\main\\webapp\\resources\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			file.delete();
			
			// 확장자명 list TF result 하는것 필요
			/*String[] Ext = new String []{"jpg", "jpeg", "png", "gif"};*/
			/*if(fileExtention.equals("jpg")||fileExtention.equals("png")||fileExtention.equals("jpeg")||fileExtention.equals("gif")){*/
			if(type.equals("image")){
			
				String uuidFileName = file.getAbsolutePath().replace("s_", "");
				
				file = new File(uuidFileName);
				
				file.delete();
				
			}
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	}
	
	
	
}// controller end
