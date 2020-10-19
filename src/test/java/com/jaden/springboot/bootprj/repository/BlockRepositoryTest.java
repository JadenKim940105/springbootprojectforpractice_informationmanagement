package com.jaden.springboot.bootprj.repository;

import com.jaden.springboot.bootprj.domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BlockRepositoryTest {
    @Autowired
    private BlockRepository blockRepository;

    @Test
    void crud(){
        Block block = new Block();
        block.setName("martin");
        block.setReason("친하지 않아서");
        block.setStartDate(LocalDate.now());
        block.setEndDate(LocalDate.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        blocks.forEach(System.out::println);

        assertThat(blocks.size()).isEqualTo(3);
        assertThat(blocks.get(0).getName()).isEqualTo("dennis");
        assertThat(blocks.get(1).getName()).isEqualTo("sophia");
        assertThat(blocks.get(2).getName()).isEqualTo("martin");
    }

}