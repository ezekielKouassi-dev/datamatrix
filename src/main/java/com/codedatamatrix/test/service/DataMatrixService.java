package com.codedatamatrix.test.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataMatrixService {
	public BufferedImage generateDataMatrix(String data) throws WriterException {
		int width = 200;
		int height = 200;
		Map<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		DataMatrixWriter writer = new DataMatrixWriter();
		BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.DATA_MATRIX, width, height, hints);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0 : 0xFFFFFF);
			}
		}
		return image;
	}

}
