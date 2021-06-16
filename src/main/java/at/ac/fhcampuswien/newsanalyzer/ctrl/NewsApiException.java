package at.ac.fhcampuswien.newsanalyzer.ctrl;


public class NewsApiException extends Exception {
    public NewsApiException(String error){
        super(error);
    }
}
