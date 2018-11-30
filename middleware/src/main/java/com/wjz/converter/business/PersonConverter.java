package com.wjz.converter.business;

import com.wjz.annotation.Convertor;
import com.wjz.converter.JsonConverter;
import com.wjz.pojo.dto.PersonDTO;

@Convertor(target = PersonDTO.class)
public class PersonConverter extends JsonConverter<PersonDTO> {

}
