package br.com.trier.centerhotels.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import br.com.trier.centerhotels.BaseTests;
import br.com.trier.centerhotels.models.RoomType;
import br.com.trier.centerhotels.services.exceptions.ObjectNotFound;
import jakarta.transaction.Transactional;

@Transactional
@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts ="classpath:/resources/sqls/room_type.sql")  
public class RoomTypeTest extends BaseTests {

	@Autowired
	RoomTypeService typeService;
	
	@Test
	@DisplayName("Update type")
	void update() {	
		RoomType r = new RoomType(10, "desc", 3, 1000.0f);
		typeService.update(r);
		assertEquals(3, typeService.listAll().size());
	}
	
	@Test
	@DisplayName("Procura por diária")
	void searchByDaily() {	
		assertEquals(1, typeService.findByDailyOrderByQt(100.0f).size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		typeService.findByDailyOrderByQt(101.0f).size());
		assertEquals("Nenhum quarto com valor diário de : 101.0", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por diária entre")
	void searchByDailyBetween() {	
		assertEquals(2, typeService.findByDailyBetweenOrderByQt(100.0f, 150.0f).size());
		assertEquals(3, typeService.findByDailyBetweenOrderByQt(0.0f, 300.0f).size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		typeService.findByDailyBetweenOrderByQt(1.0f, 10.0f).size());
		assertEquals("Nenhum quarto com valor diário entre : 1.0 e 10.0", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por descrição")
	void searchByDesc() {	
		assertEquals(2, typeService.findByDescriptionContainingIgnoreCaseOrderByDaily("s").size());
		assertEquals(1, typeService.findByDescriptionContainingIgnoreCaseOrderByDaily("x").size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		typeService.findByDescriptionContainingIgnoreCaseOrderByDaily("y").size());
		assertEquals("Nenhum tipo com descrição contendo : y", ex.getMessage());
	}
	
	@Test
	@DisplayName("Procura por quantidade máxima de pessoas")
	void searchByQt() {	
		assertEquals(2, typeService.findByQtOrderByDaily(2).size());
		assertEquals(1, typeService.findByQtOrderByDaily(4).size());
		var ex = assertThrows(ObjectNotFound.class, () ->
		typeService.findByQtOrderByDaily(3).size());
		assertEquals("Nenhum quarto com quantidade máxima de pessoas igual á : 3", ex.getMessage());
	}
}
