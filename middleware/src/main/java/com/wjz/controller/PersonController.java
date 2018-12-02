package com.wjz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wjz.converter.Converter;
import com.wjz.converter.ConverterRegistry;
import com.wjz.pojo.dto.PersonDTO;

@RestController
@RequestMapping("person")
public class PersonController {

	@Autowired
	private ConverterRegistry<PersonDTO> registry;

	@PostMapping("catch")
	public String catchPerson(@RequestParam("data") String ciphertext) {
		try {
			Converter<PersonDTO> converter = registry.getConverter(PersonDTO.class);
			PersonDTO dto = converter.stringConvert2Object(ciphertext);
			System.out.println(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "catchPerson";
	}

}
