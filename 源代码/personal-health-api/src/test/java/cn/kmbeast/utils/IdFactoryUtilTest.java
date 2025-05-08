package cn.kmbeast.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdFactoryUtilTest {

    @Test
    void testGetFileId_Length() {
        String fileId = IdFactoryUtil.getFileId();
        assertEquals(7, fileId.length(), "The length of the file ID should be 7 characters.");
    }

    @Test
    void testGetFileId_NotNull() {
        String fileId = IdFactoryUtil.getFileId();
        assertNotNull(fileId, "The file ID should not be null.");
    }

    @Test
    void testGetFileId_Unique() {
        String fileId1 = IdFactoryUtil.getFileId();
        String fileId2 = IdFactoryUtil.getFileId();
        assertNotEquals(fileId1, fileId2, "The file IDs should be unique.");
    }

    @Test
    void testGetFileId_Format() {
        String fileId = IdFactoryUtil.getFileId();
        // Check if the file ID consists of alphanumeric characters (upper or lowercase)
        assertTrue(fileId.matches("[a-zA-Z0-9]{7}"), "The file ID should only contain alphanumeric characters.");
    }
}
