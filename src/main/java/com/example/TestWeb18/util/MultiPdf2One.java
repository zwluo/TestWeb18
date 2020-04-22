package com.example.TestWeb18.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.util.List;

public class MultiPdf2One
{
	public static void createUnitedPdfByItext(String fileName, String filePath , List<String> srcFiles) throws Exception {
		try {
			System.out.println("=======================================" + fileName + " 合并开始=======================================");
			System.out.println("mergetPdf" + Thread.currentThread().getName());
			if("abc合并后.pdf".equalsIgnoreCase(fileName)) {
				Thread.sleep(5000);
			}
			Document document = new Document();
			PdfCopy copy = new PdfCopy(document, new FileOutputStream(filePath + fileName));
			document.open();
			for (int i = 0; i < srcFiles.size(); i++) {
				PdfReader reader = new PdfReader(srcFiles.get(i));
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
				reader.close();
			}
			document.close();
			System.out.println("=======================================" + fileName + " 合并结束=======================================");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("附件转pdf发生错误。");
		}
	}

}
