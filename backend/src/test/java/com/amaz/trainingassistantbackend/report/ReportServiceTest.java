package com.amaz.trainingassistantbackend.report;

import com.amaz.trainingassistantbackend.service.ExerciseService;
import com.amaz.trainingassistantbackend.service.ReportService;

import java.util.List;

public class ReportServiceTest {
    private ReportService reportService;
    private ExerciseService exerciseService;
    //private List<Animal> animals;

    /*@Before
    public void before() {
        animals = new LinkedList<>();
        for(int i = 0; i < 5;i++) {
            animals.add(new Animal());
        }

        exerciseService = mock(ExerciseService.class);
       // when(exerciseService.getMaxAnimals()).thenReturn(12);
        when(exerciseService.findAllAnimals()).thenReturn(animals);

        reportService = new ReportService(exerciseService);
    }

    @Test
    public void getShelterReport() {
        ShelterReport shelterReport = reportService.getShelterReport();

        verify(exerciseService, times(1)).getMaxAnimals();
        verify(exerciseService, times(1)).findAllAnimals();

        assertEquals(animals, shelterReport.getAnimals());
        assertEquals(12, shelterReport.getMaxAnimals());
        assertEquals(ShelterStatus.HAS_PLACE, shelterReport.getShelterStatus());
        assertEquals(5, shelterReport.getOccupiedPlaces());
    }*/
}