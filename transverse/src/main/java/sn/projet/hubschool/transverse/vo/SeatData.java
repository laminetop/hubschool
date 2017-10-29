package sn.projet.hubschool.transverse.vo;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeName("SeatDocument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatData {

    private static final long serialVersionUID = 2406161152587012949L;

    private String codeCategoryTicket;
}
