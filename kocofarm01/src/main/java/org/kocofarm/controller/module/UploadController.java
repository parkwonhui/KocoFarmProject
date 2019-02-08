package org.kocofarm.controller.module;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.kocofarm.domain.fileRoom.AttachFileVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {

	/*@GetMapping("/uploadForm") // form 방식
	public void uploadForm() {

		log.info("uploadform");

	}*/

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("uploadAjax");

	}

	/*
	 * @PostMapping("/uploadFormAction") // multipart방식 public void
	 * uploadFormPost(MultipartFile[] uploadFile, Model model) {
	 * 
	 * String uploadFolder =
	 * "C:\\Users\\KOSTA\\git\\kocofarm01\\kocofarm01\\src\\main\\webapp\\resources\\upload";
	 * 
	 * for (MultipartFile multipartFile : uploadFile) {
	 * 
	 * log.info("----------------------------------------------------------");
	 * log.info("Upload File Name :" + multipartFile.getOriginalFilename());
	 * log.info("Upload File Size :" + multipartFile.getSize());
	 * 
	 * File saveFile = new File(uploadFolder,
	 * multipartFile.getOriginalFilename()); try {
	 * multipartFile.transferTo(saveFile); } catch (Exception e) {
	 * log.error(e.getMessage()); }
	 * 
	 * }
	 * 
	 * }
	 */

	// 이미지 파일 여부 판단
	private boolean checkImageType(File file) {

		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;

	}

	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	@PostMapping("/uploadAjaxAction")
	@ResponseBody
	public ResponseEntity<List<AttachFileVO>> uploadAjaxPost(MultipartFile[] uploadFile) {

		List<AttachFileVO> list = new ArrayList<>();

		String uploadFolder = "C:\\Users\\KOSTA\\git\\kocofarm01\\kocofarm01\\src\\main\\webapp\\resources\\upload";

		String uploadFolderPath = getFolder();
		// makeFolder--------------
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("uploadPath : " + uploadPath);

		if (uploadPath.exists() == false) {

			uploadPath.mkdirs();
		}
		// make yyyyy/mm/dd

		for (MultipartFile multipartFile : uploadFile) {

			log.info("______________________________________________");
			log.info("uploadFile : " + multipartFile.getOriginalFilename());
			log.info("uploadSize : " + multipartFile.getSize());

			AttachFileVO attachFileVO = new AttachFileVO();

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);

			UUID uuid = UUID.randomUUID();
			// 임의의 값을 생성합니다.
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			// 파일이름음 임의의 값 + 기존 실제 파일 이름으로 바꿉니다.

			try {
				File saveFile = new File(uploadFolder, uploadFileName);
				// 바꾼 이름값을 저장합니다.
				multipartFile.transferTo(saveFile);

				attachFileVO.setUuid(uuid.toString());
				attachFileVO.setFile_path(uploadFolderPath);

				// chheck image type file
				if (checkImageType(saveFile)) {

					attachFileVO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);

					thumbnail.close();
				}

				// add to List
				list.add(attachFileVO);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // end catch

		}
		
		// end for
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
}