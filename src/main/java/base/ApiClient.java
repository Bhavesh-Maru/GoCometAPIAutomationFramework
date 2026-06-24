package base;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Post;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import utility.PropertyClass;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiClient {
	
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	
	PropertyClass prop;

	public ApiClient() throws IOException {
		prop = new PropertyClass();
		
		requestSpec = new RequestSpecBuilder()
				.setBaseUri(prop.getUrl())
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.build();
		
		responseSpec = new ResponseSpecBuilder()
				.build();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getPosts() {
		String res = given()
					.spec(requestSpec)
					.log().all()
				.when()
					.get(Endpoints.POSTS)
				.then()
					.spec(responseSpec)
					.log().all()
					.extract()
					.asString();
		
		ObjectMapper mapper = new ObjectMapper();
		List<Post> posts = mapper.readValue(res, new TypeReference<List<Post>>(){});
		
		return posts;
	}
	
	public Post getPostById(int id) {
		return given()
					.spec(requestSpec)
					.log().all()
				.when()
					.get(Endpoints.POSTS + "/" + id)
				.then()
					.spec(responseSpec)
					.log().all()
					.extract()
					.as(Post.class);
	}
	
	public Post createPosts(Post body) {
		return given()
					.spec(requestSpec)
					.body(body)
					.log().all()
				.when()
					.post(Endpoints.POSTS)
				.then()
					.spec(responseSpec)
					.log().all()
					.extract()
					.as(Post.class);
	}
	
	public Response deletePostById(int id) {
		return given()
					.spec(requestSpec)
					.pathParam("id", id)
					.log().all()
				.when()
					.delete(Endpoints.POSTS_BY_ID)
				.then()
					.spec(responseSpec)
					.log().all()
					.extract()
					.response();
	}
}
