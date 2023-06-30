package br.com.trier.centerhotels.resources;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.CenterHotelsApplication;
import br.com.trier.centerhotels.config.jwt.LoginDTO;
import br.com.trier.centerhotels.models.RoomType;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/room_type.sql")
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/resources/sqls/user.sql")
@SpringBootTest(classes = CenterHotelsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomTypeResourceTest {

	@Autowired
	protected TestRestTemplate rest;
	

	
	@SuppressWarnings("unused")
	private ResponseEntity<RoomType> getUser(String url, HttpHeaders headers) {
		return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), RoomType.class);
	}

	private ResponseEntity<List<RoomType>> getUsers(String url, HttpHeaders headers) {
		return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<List<RoomType>>() {
		});
	}
	
	@Test
	@DisplayName("Buscar por id")
	public void testGetOk() {

		HttpHeaders headers = getHeader("user1@example.com", "password1");
	    ResponseEntity<RoomType> response = rest.exchange("/room-types/10", HttpMethod.GET, new HttpEntity<>(headers), RoomType.class);
	    assertEquals(response.getStatusCode(), HttpStatus.OK);

	    RoomType user = response.getBody();
	    assertEquals("Standard", user.getDescription());
	}

	@Test
	@DisplayName("Buscar por id inexistente")
	public void testGetNotFound() {
			HttpHeaders headers = getHeader("user1@example.com", "password1");
		    ResponseEntity<RoomType> response = rest.exchange("/room-types/100", HttpMethod.GET, new HttpEntity<>(headers), RoomType.class);
		    assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	@DisplayName("Cadastrar usuário")
	public void testCreateUser() {
			RoomType r = new RoomType(null, "room", null, null);
		 	HttpHeaders headers = getHeader("user1@example.com", "password1");
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    HttpEntity<RoomType> requestEntity = new HttpEntity<RoomType>(r, headers);
		    ResponseEntity<RoomType> responseEntity = rest.exchange(
		            "/room-types",
		            HttpMethod.POST,
		            requestEntity,
		            RoomType.class
		    );
		    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		    RoomType user = responseEntity.getBody();
		    assertEquals("room", user.getDescription());
	}
	
	@Test
	@DisplayName("Atualizar usuário")
	public void testUpdateUser() {

		RoomType r = new RoomType(null, "room", null, null);
		    HttpHeaders headers = getHeader("user1@example.com", "password1");
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    HttpEntity<RoomType> requestEntity = new HttpEntity<RoomType>(r, headers);
		    ResponseEntity<RoomType> responseEntity = rest.exchange("/room-types/10", HttpMethod.PUT, requestEntity,
		            RoomType.class);
		    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		    RoomType user = responseEntity.getBody();
		    assertEquals("room", user.getDescription());
	}
	
	@Test
	@DisplayName("Excluir usuário")
	public void testDeleteUser() {
		    ResponseEntity<Void> responseEntity = rest.exchange("/room-types/10", HttpMethod.DELETE, new HttpEntity<>(getHeader("user1@example.com", "password1")), Void.class);
		    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	@DisplayName("Excluir usuário que não existe")
	public void testDeleteUserNonExist() {
	    ResponseEntity<Void> responseEntity = rest.exchange("/room-types/100", HttpMethod.DELETE, new HttpEntity<>(getHeader("user1@example.com", "password1")), Void.class);
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	@DisplayName("Listar todos os usuários")
	public void testListAllUsers() {
		    ResponseEntity<List<RoomType>> responseEntity = getUsers("/room-types", getHeader("user1@example.com", "password1"));
		    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		    List<RoomType> roomTypes = responseEntity.getBody();
		    assertEquals(3, roomTypes.size());
	}
	
	
	@Test
	@DisplayName("Buscar tipos por diaria")
	public void testFindByDaily() {
	    ResponseEntity<List<RoomType>> responseEntity = getUsers("/room-types/daily/100.0", getHeader("user1@example.com", "password1"));
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	    List<RoomType> roomTypes = responseEntity.getBody();
	    assertEquals(1, roomTypes.size());
	}
	
	@Test
	@DisplayName("Buscar tipos por diaria entre")
	public void testFindByDailyBetween() {
	    ResponseEntity<List<RoomType>> responseEntity = getUsers("/room-types/daily-between/100/300", getHeader("user1@example.com", "password1"));
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	    List<RoomType> roomTypes = responseEntity.getBody();
	    assertEquals(3, roomTypes.size());
	}
	
	@Test
	@DisplayName("Buscar tipos por descrição")
	public void testFindByDesc() {
	    ResponseEntity<List<RoomType>> responseEntity = getUsers("/room-types/desc/S", getHeader("user1@example.com", "password1"));
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	    List<RoomType> roomTypes = responseEntity.getBody();
	    assertEquals(2, roomTypes.size());
	}
	
	@Test
	@DisplayName("Buscar tipos por quantidade máxima")
	public void testFindByQt() {
	    ResponseEntity<List<RoomType>> responseEntity = getUsers("/room-types/qt/2", getHeader("user1@example.com", "password1"));
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	    List<RoomType> roomTypes = responseEntity.getBody();
	    assertEquals(2, roomTypes.size());
	}
	
	
	private HttpHeaders  getHeader(String email, String senha) {
		LoginDTO loginDTO = new LoginDTO(email, senha);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<LoginDTO> requestEntity = new HttpEntity<>(loginDTO, headers);
	    ResponseEntity<String> responseEntity = rest.exchange(
	            "/auth/token",
	            HttpMethod.POST,
	            requestEntity,
	            String.class
	    );
	    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	    HttpHeaders headersR = new HttpHeaders();
	    headersR.setBearerAuth(responseEntity.getBody());
	    return headersR;
	}


}
