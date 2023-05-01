package com.codedatamatrix.test.controller;

import com.codedatamatrix.test.service.DataMatrixService;
import com.google.zxing.WriterException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("api/datamatrix")
@CrossOrigin(origins = "http://localhost:4200")
public class DataMatrixController {
	private final DataMatrixService dataMatrixService;

	public DataMatrixController(DataMatrixService dataMatrixService) {
		this.dataMatrixService = dataMatrixService;
	}

	@GetMapping("/image")
	public ResponseEntity<byte[]> getImage() throws IOException, WriterException {
		BufferedImage image = dataMatrixService.generateDataMatrix("sdkjfhkljdhdkjhdskshlkdsfhjfhlskhfjkdfjskfls");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(image, "png", baos);
		byte[] imageData = baos.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
	}

}
