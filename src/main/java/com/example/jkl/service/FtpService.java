package com.example.jkl.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FtpService {
    public boolean upload(List<File> fileList) throws IOException;
}
