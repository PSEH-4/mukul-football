package io.testcase.football.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import io.test.football.entity.Country;
import io.test.football.exception.DataNotFoundException;
import io.test.football.repository.CountryRepository;
import io.test.football.service.CountryService;
import io.test.football.service.impl.CountryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CountryServiceTest {

	@Mock
	CountryRepository repository;

	@InjectMocks
	CountryService service = new CountryServiceImpl();

	@Before
	public void setUp() {
		List<Country> list = Arrays.asList(new Country(), new Country());
		Mockito.when(repository.findAll()).thenReturn(list);
	}

	@Test
	public void testGetAll() {
		List<Country> list = service.getAll();
		assertEquals(list.size(), 2);
	}

	@Test
	public void testGetById() {

		Mockito.when(repository.getOne(any())).thenReturn(new Country());
		try {
			assertNotNull(service.getById(2));
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = DataNotFoundException.class)
	public void testException() throws DataNotFoundException {
		Mockito.doThrow(new DataNotFoundException()).when(repository.getOne(any()));

		service.getById(2);

	}

}
