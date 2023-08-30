package org.example.files.filesFactory;

import org.example.files.FileReader;
import org.example.files.FileReaderFileImage;
import org.example.files.FileReaderFileText;

import java.util.*;
import java.util.regex.*;

public class FilesFactoryImplementation implements FilesFactoryInterface {

    private final Map<String, String> contentTypeMap = new HashMap<String, String>() {{
        put(".html", "text/html");
        put(".js", "text/javascript");
        put(".css", "text/css");
        put(".jpg", "image/jpg");
        put(".png", "image/javascript");
        put(".gif", "image/gif");
    }};
   private String contentType;
    @Override
    public FileReader getInstance(String resource) throws Exception {
        if (matchRegex(".(jpg|png|ico|gif)$", resource)) {
            return new FileReaderFileImage(contentType);
        }
        if(matchRegex(".(html|js|css|)$", resource)) {
            return new FileReaderFileText(contentType);
        }
        throw new Exception();
    }

    private Boolean matchRegex(String regex, String resource) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(resource);
        if (matcher.find()) {
            contentType = contentTypeMap.get(matcher.group());
            return true;
        }
        return false;
    };
}
