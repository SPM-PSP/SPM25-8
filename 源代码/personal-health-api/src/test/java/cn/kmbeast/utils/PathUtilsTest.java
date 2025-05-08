package cn.kmbeast.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URL;

// Note: These tests are designed to verify that the correct substring logic
// from the PathUtils class is applied based on the simulated OS name.
// They still rely on ClassLoader.getResource("") returning a non-null URL
// with a valid path string in the test environment.
// Due to the constraint not to use mocking or refactor the original code,
// these tests cannot fully guarantee the *correctness* of the resulting path
// for actual file system operations across all environments, only that the
// specific string manipulations defined in the PathUtils class are executed.
public class PathUtilsTest {

    private String originalOsName;
    private String testPrePath; // To store the prePath calculated in the test environment

    // Store the original OS name and calculate the prePath before each test
    @BeforeEach
    void setUp() throws UnsupportedEncodingException {
        originalOsName = System.getProperty("os.name");

        // Calculate the prePath once based on the actual test environment
        // This is what the PathUtils code does internally.
        URL resourceUrl = PathUtils.class.getClassLoader().getResource("");
        assertNotNull(resourceUrl, "ClassLoader.getResource(\"\") should return a URL in the test environment");
        String decodedPath = URLDecoder.decode(resourceUrl.getPath(),"utf-8");
        testPrePath = decodedPath.replace("/target/classes", "");
        assertNotNull(testPrePath, "prePath should not be null after decoding and replacing");
        assertFalse(testPrePath.isEmpty(), "prePath should have a reasonable length for testing substring operations");
    }

    // Restore the original OS name after each test
    @AfterEach
    void tearDown() {
        System.setProperty("os.name", originalOsName);
    }

    // Test case for Windows operating system
    @Test
    void testGetClassLoadRootPath_Windows() {
        // Set the OS name to simulate Windows
        System.setProperty("os.name", "Windows 10");

        // Call the method under test
        String rootPath = PathUtils.getClassLoadRootPath();

        // Assertions based on the code's logic for Windows: substring(1, length - 1)
        // We verify that the resulting path is derived by removing the first and last characters
        // from the calculated prePath in this test environment.
        assertEquals(testPrePath.length() > 1 ? testPrePath.substring(1, testPrePath.length() - 1) : "",
                rootPath,
                "Root path on Windows should be substring(1, length - 1) of the prePath");

        // Additional checks based on the substring operation
        if (testPrePath.length() > 1) {
            assertFalse(rootPath.startsWith(String.valueOf(testPrePath.charAt(0))),
                    "Root path on Windows should not start with the original prePath's first character");
            assertFalse(rootPath.endsWith(String.valueOf(testPrePath.charAt(testPrePath.length() - 1))),
                    "Root path on Windows should not end with the original prePath's last character");
            assertEquals(testPrePath.length() - 2, rootPath.length(),
                    "Root path length on Windows should be prePath length minus 2");
        } else {
            assertEquals("", rootPath, "Root path on Windows should be empty if prePath length is not > 1");
        }
    }

    // Test case for Mac operating system
    @Test
    void testGetClassLoadRootPath_Mac() {
        // Set the OS name to simulate Mac
        System.setProperty("os.name", "Mac OS X");

        // Call the method under test
        String rootPath = PathUtils.getClassLoadRootPath();

        // Assertions based on the code's logic for Mac: substring(0, length - 1)
        // We verify that the resulting path is derived by removing the last character
        // from the calculated prePath in this test environment.
        assertEquals(!testPrePath.isEmpty() ? testPrePath.substring(0, testPrePath.length() - 1) : "",
                rootPath,
                "Root path on Mac should be substring(0, length - 1) of the prePath");

        // Additional checks based on the substring operation
        if (!testPrePath.isEmpty()) {
            assertTrue(rootPath.startsWith(String.valueOf(testPrePath.charAt(0))),
                    "Root path on Mac should start with the original prePath's first character");
            assertFalse(rootPath.endsWith(String.valueOf(testPrePath.charAt(testPrePath.length() - 1))),
                    "Root path on Mac should not end with the original prePath's last character");
            assertEquals(testPrePath.length() - 1, rootPath.length(),
                    "Root path length on Mac should be prePath length minus 1");
        } else {
            assertEquals("", rootPath, "Root path on Mac should be empty if prePath length is not > 0");
        }
    }

    // Test case for Linux operating system
    @Test
    void testGetClassLoadRootPath_Linux() {
        // Set the OS name to simulate Linux
        System.setProperty("os.name", "Linux");

        // Call the method under test
        String rootPath = PathUtils.getClassLoadRootPath();

        // Assertions based on the code's logic for Linux: substring(0, length - 1)
        // Same logic as Mac.
        assertEquals(!testPrePath.isEmpty()? testPrePath.substring(0, testPrePath.length() - 1) : "",
                rootPath,
                "Root path on Linux should be substring(0, length - 1) of the prePath");

        // Additional checks based on the substring operation
        if (!testPrePath.isEmpty()) {
            assertTrue(rootPath.startsWith(String.valueOf(testPrePath.charAt(0))),
                    "Root path on Linux should start with the original prePath's first character");
            assertFalse(rootPath.endsWith(String.valueOf(testPrePath.charAt(testPrePath.length() - 1))),
                    "Root path on Linux should not end with the original prePath's last character");
            assertEquals(testPrePath.length() - 1, rootPath.length(),
                    "Root path length on Linux should be prePath length minus 1");
        } else {
            assertEquals("", rootPath, "Root path on Linux should be empty if prePath length is not > 0");
        }
    }

    // Test case for Unix operating system
    @Test
    void testGetClassLoadRootPath_Unix() {
        // Set the OS name to simulate Unix
        System.setProperty("os.name", "Unix");

        // Call the method under test
        String rootPath = PathUtils.getClassLoadRootPath();

        // Assertions based on the code's logic for Unix: substring(0, length - 1)
        // Same logic as Mac and Linux.
        assertEquals(!testPrePath.isEmpty() ? testPrePath.substring(0, testPrePath.length() - 1) : "",
                rootPath,
                "Root path on Unix should be substring(0, length - 1) of the prePath");

        // Additional checks based on the substring operation
        if (!testPrePath.isEmpty()) {
            assertTrue(rootPath.startsWith(String.valueOf(testPrePath.charAt(0))),
                    "Root path on Unix should start with the original prePath's first character");
            assertFalse(rootPath.endsWith(String.valueOf(testPrePath.charAt(testPrePath.length() - 1))),
                    "Root path on Unix should not end with the original prePath's last character");
            assertEquals(testPrePath.length() - 1, rootPath.length(),
                    "Root path length on Unix should be prePath length minus 1");
        } else {
            assertEquals("", rootPath, "Root path on Unix should be empty if prePath length is not > 0");
        }
    }

    // Test case for any other operating system falling into the 'else' branch
    @Test
    void testGetClassLoadRootPath_OtherOS() {
        // Set the OS name to simulate an unknown OS
        System.setProperty("os.name", "SomeOtherOS");

        // Call the method under test
        String rootPath = PathUtils.getClassLoadRootPath();

        // Assertions based on the code's logic for the 'else' branch: substring(1, length - 1)
        // Same logic as Windows.
        assertEquals(testPrePath.length() > 1 ? testPrePath.substring(1, testPrePath.length() - 1) : "",
                rootPath,
                "Root path on OtherOS should be substring(1, length - 1) of the prePath");

        // Additional checks based on the substring operation
        if (testPrePath.length() > 1) {
            assertFalse(rootPath.startsWith(String.valueOf(testPrePath.charAt(0))),
                    "Root path on OtherOS should not start with the original prePath's first character");
            assertFalse(rootPath.endsWith(String.valueOf(testPrePath.charAt(testPrePath.length() - 1))),
                    "Root path on OtherOS should not end with the original prePath's last character");
            assertEquals(testPrePath.length() - 2, rootPath.length(),
                    "Root path length on OtherOS should be prePath length minus 2");
        } else {
            assertEquals("", rootPath, "Root path on OtherOS should be empty if prePath length is not > 1");
        }
    }

    // Test case to ensure UnsupportedEncodingException is caught (though unlikely for "utf-8")
    // Note: It's very difficult to trigger UnsupportedEncodingException for "utf-8"
    // in a standard JVM test environment without complex setup or mocking.
    // This test primarily verifies that the method doesn't throw an unhandled exception.
    @Test
    void testGetClassLoadRootPath_EncodingExceptionHandled() {
        // We cannot easily force URLDecoder to throw UnsupportedEncodingException for "utf-8".
        // This test just confirms that calling the method does not throw an unhandled exception.
        // The side effect (printing stack trace) is not easily testable without capturing System.err.
        assertDoesNotThrow(PathUtils::getClassLoadRootPath, "Method should not throw unhandled exceptions");
    }
}
