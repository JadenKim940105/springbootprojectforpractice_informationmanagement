package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {

}
