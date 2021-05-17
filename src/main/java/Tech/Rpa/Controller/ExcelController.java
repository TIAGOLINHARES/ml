package Tech.Rpa.Controller;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Tech.Rpa.model.Produto;

public class ExcelController {

	public void writeOuteputFile(List<Produto> produtos) {

		try {

			// xlsx 
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Todos Produtos");
			
			int indexRow = 0;
			for (Produto produto : produtos) {
				Row row = sheet.createRow(indexRow++);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(produto.getNome());
				
				cell = row.createCell(1);
				cell.setCellValue(produto.getPreco());
				
				
			}
			
			FileOutputStream fileOutput = new FileOutputStream("C:\\Users\\Tiago Linhares\\Documents\\xls\\arquivo.xlsx");
			workbook.write(fileOutput);
			workbook.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
