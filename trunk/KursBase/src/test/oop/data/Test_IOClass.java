package test.oop.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ru.etu.oop.data.IOClass;


public class Test_IOClass {
	
	@Test
	public void test_DATA_FILE_FieldCorrect() {
		assertEquals("./data/data.txt", IOClass.getDataFile());
	}
	
	@Test
	public void test_WORKER_FILE_FieldCorrect() {
		assertEquals("./data/workers.txt", IOClass.getWorkerFile());
	}	
	
	@Test
	public void test_DATA_FILE_FileExist() {
		assertTrue(new java.io.File("./data/data.txt").exists());
	}
	
	@Test
	public void test_WORKER_FILE_FileExist() {
		assertTrue(new java.io.File("./data/workers.txt").exists());
	}	
	
}
