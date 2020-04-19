package testcases;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import utilities.dataDriven;

public class TC11_dataDrivenDataFromExcel {

	@Test
	public void printDataFromExcel() throws IOException {

		dataDriven d = new dataDriven();
		ArrayList data = d.getData("Add Profile");

		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
		
	
	}

}
