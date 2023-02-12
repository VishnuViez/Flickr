package com.example.flickr.data;


import com.example.flickr.model.Photo;

public class URIBuilder {

    private static final String BASE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=d3ccf0bd600b95a65da81044a9f4d604" +
            "&format=json&nojsoncallback=1&safe_search=1";

    private int page;
    private String query;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString()
    {
        String url = BASE_URL.concat("&text=" + query).concat("&page=" + page);
        return url;
    }

    public String toPhotoURI(Photo photo)
    {
        return "https://farm".concat(String.valueOf(photo.getFarm())).concat(".static.flickr.com/")
                .concat(photo.getServer()).concat("/").concat(photo.getId())
                .concat("_").concat(photo.getSecret()).concat(".jpg");

    }
}
