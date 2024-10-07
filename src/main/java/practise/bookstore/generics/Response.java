package practise.bookstore.generics;

import lombok.Data;

@Data
public class Response <T> {

    private T model;

    private String message;

    public Response() {
    }

    public Response(T model, String message) {
        this.model = model;
        this.message = message;
    }

}
