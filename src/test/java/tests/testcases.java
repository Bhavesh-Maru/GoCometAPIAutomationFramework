package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pojo.Post;

public class testcases extends BaseTest {
	SoftAssert softAssert = new SoftAssert();
	int id = 0;
	int userId = RandomUtils.nextInt();
	String title = RandomStringUtils.randomAlphabetic(7);
	String body = RandomStringUtils.randomAlphabetic(20);
	
	@Test(priority = 1)
	public void createPost() {
		Post requestBody = new Post();
		requestBody.setUserId(userId);
		requestBody.setTitle(title);
		requestBody.setBody(body);
				
		Post response = apiClient.createPosts(requestBody);
		
		id = response.getId();
		
		softAssert.assertEquals(response.getUserId(), userId);
		softAssert.assertEquals(response.getTitle(), title);
		softAssert.assertEquals(response.getBody(), body);
		
		softAssert.assertAll();
	}
	
	@Test(priority = 2, enabled = false)
	public void getPostById() {
		Post post = apiClient.getPostById(id);
		
		softAssert.assertEquals(post.getUserId(), userId);
		softAssert.assertEquals(post.getTitle(), title);
		softAssert.assertEquals(post.getBody(), body);
		softAssert.assertAll();
	}
	
	@Test(priority = 3)
	public void deletePost() {
		apiClient.deletePostById(id);
	}
}
