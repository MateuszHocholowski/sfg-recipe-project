package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UnitOfMeasureServiceImplTest {

    @InjectMocks
    private UnitOfMeasureServiceImpl service;
    @Mock
    private UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    @Test
    public void listAllUoms() {

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
        unitOfMeasure2.setId(2L);
        Set<UnitOfMeasure> unitOfMeasures = Set.of(unitOfMeasure1,unitOfMeasure2);

        UnitOfMeasureCommand unitOfMeasureCommand1 = new UnitOfMeasureCommand();
        unitOfMeasure1.setId(1L);
        UnitOfMeasureCommand unitOfMeasureCommand2 = new UnitOfMeasureCommand();
        unitOfMeasure2.setId(2L);
//        Set<UnitOfMeasureCommand> unitOfMeasureCommands = Set.of(unitOfMeasureCommand1,unitOfMeasure2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure1)).thenReturn(unitOfMeasureCommand1);
        when(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure2)).thenReturn(unitOfMeasureCommand2);

        Set<UnitOfMeasureCommand> listedUoms = service.listAllUoms();

        assertNotNull(listedUoms);
        assertEquals(2,listedUoms.size());
        verify(unitOfMeasureRepository,times(1)).findAll();
    }
}