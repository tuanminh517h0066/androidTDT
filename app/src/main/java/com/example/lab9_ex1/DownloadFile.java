package com.example.lab9_ex1;

public class DownloadFile {
    String fileName;
    double size;
    int status;
    int progress;

    public DownloadFile(String fileName, double size, int status, int progress) {
        this.fileName = fileName;
        this.size = size;
        this.status = status;
        this.progress = progress;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getImgResourceId(){
        int ResId= R.drawable.icon_other;
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        switch (ext){
            case "docx":
                ResId= R.drawable.icon_office;
                break;
            case "jpg":
                ResId= R.drawable.icon_image;
                break;
            case "mp3":
                ResId= R.drawable.icon_music;
                break;
            case "txt":
                ResId=R.drawable.icon_text;
                break;
            case "zip":
                ResId=R.drawable.icon_archive;
                break;

        }

        return ResId;
    }
}
