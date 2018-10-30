package com.github.ftfetter.sales.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void isValidExtensionSuccessTest() {
        Assert.assertTrue(FileUtils.isValidExtension("test.dat"));
    }

    @Test
    public void isValidExtensionFailureTest() {
        Assert.assertFalse(FileUtils.isValidExtension("test.txt"));
    }

    @Test
    public void toOutputFileTest() {
        File outputFile = FileUtils.toOutputFile("test", "test/out");
        Assert.assertEquals("test.done.dat", outputFile.getName());
        Assert.assertEquals("test\\out\\test.done.dat", outputFile.getPath());
    }

    @Test
    public void toOutputFileWithInputExtensionTest() {
        File outputFile = FileUtils.toOutputFile("test.dat", "test/out");
        Assert.assertEquals("test.done.dat", outputFile.getName());
        Assert.assertEquals("test\\out\\test.done.dat", outputFile.getPath());
    }

    @Test
    public void toInputFileTest() {
        File inputFile = FileUtils.toInputFile("test", "test/in");
        Assert.assertEquals("test.dat", inputFile.getName());
        Assert.assertEquals("test\\in\\test.dat", inputFile.getPath());
    }

    @Test
    public void toInputFileWithInputExtensionTest() {
        File inputFile = FileUtils.toInputFile("test.dat", "test/in");
        Assert.assertEquals("test.dat", inputFile.getName());
        Assert.assertEquals("test\\in\\test.dat", inputFile.getPath());
    }
}