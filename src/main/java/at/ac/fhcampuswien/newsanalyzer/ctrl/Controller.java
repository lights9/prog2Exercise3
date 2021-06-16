package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import at.ac.fhcampuswien.newsapi.enums.Language;


import java.util.*;
import java.util.stream.Collectors;

//worked with colleagues Zeynep Avci, Greta Berdynaj, Parsa Salvatian
public class Controller {

    public static final String APIKEY = "64a3d2e6811d4505adf39b806396e5dd";  //TODO add your api key


    String providerWithMostArticles = "";

    public void process(String q, Endpoint endpoint, Country sourceCountry, Category sourceCategory, Language language) {
        System.out.println("Start process");

        //TODO implement Error handling


        //TODO load the news based on the parameters

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ(q)
                .setEndPoint(endpoint)
                .setSourceCountry(sourceCountry)
                .setSourceCategory(sourceCategory)
                .setLanguage(language)
                .createNewsApi();

        NewsResponse newsResponse ;
        List<Article> articles = null;



        try {
            newsResponse = newsApi.getNews();
            if (newsResponse != null) {
                articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }
            long articleNumber = analyzeSize(articles);
            System.out.println("Number of articles in this category: " + articleNumber);
            System.out.println("Author with shortest name: "+ getAuthorWithShortestName(articles));
            System.out.println("Title: "+ getLongestTitleSorted(articles));


        } catch (NewsApiException e) {
            System.out.println("ERROR: New APi not loading");
            e.printStackTrace();
        }

        //TODO implement methods for analysis

        //5b: method to analyze the provider with the most articles
        Map<String, Integer> providers = new HashMap<>();
        //Providers with most articles
        articles.stream().forEach(i -> {
            if (providers.get(i.getSource().getName()) == null) {
                providers.put(i.getSource().getName(), 1);
            } else {
                providers.put(i.getSource().getName(), providers.get(i.getSource().getName()) + 1);
            }
        });

        providerWithMostArticles = providers.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        System.out.println("\n\n Provider with most articles: " + providerWithMostArticles);

        System.out.println("End process");
    }


    public Object getData() {
        return null;
    }

    //5a: method to declare number of articles..
    public long analyzeSize(List<Article> articles) {
        return articles.stream().count();
    }




    //5c: method to get author of article..
   //https://www.logicbig.com/how-to/java-string/longest-and-shortest-string.html
    public String getAuthorWithShortestName(List<Article> authorList) {
        Article result = authorList.stream()
                .filter(a -> a.getAuthor() != null)
                .sorted(Comparator.comparingInt(a -> a.getAuthor().length()))
                .findFirst().orElse(new Article());

        return result.getAuthor();
    }


    public  List<Article> getLongestTitleSorted(List<Article> titleList){
        List title = titleList.stream().sorted(Comparator.comparingInt(s -> s.getTitle().
                length())).collect(Collectors.toList());
        Collections.reverse(title);
        return title;
    }



}
