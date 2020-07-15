package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
public enum PizzaSize {
    Small,Medium,Big,Family
}
