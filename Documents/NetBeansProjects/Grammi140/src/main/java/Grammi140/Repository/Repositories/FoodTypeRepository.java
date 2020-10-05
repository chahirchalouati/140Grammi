/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammi140.Repository.Repositories;

import Grammi140.Models.Food.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Chahir Chalouati
 */
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {

}
