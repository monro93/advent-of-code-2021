package advent.of.code.day_06

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class LanternfishPopulationSpec: Spek({
    Feature("Simulate lanternfish population") {
        val testData = mapOf(
            (initialData() to 18) to 26L,
            (initialData() to 80) to 5934L,
            (adventData() to 256) to 1695929023803L,
        )
        Scenario("Growing population with new borns") {
            testData.forEach { testCase ->
                lateinit var population: LanternfishPopulation
                Given("An initial laternfish population") {
                    population = LanternfishPopulation(
                        testCase.key.first
                            .split(",")
                            .map { Lanternfish(it.toInt()) }
                            .toMutableList()
                    )
                }
                var populationResult = 0L
                When("Simulating ${testCase.key.second} days") {
                    populationResult = population.calculatePopulationAfterDays(testCase.key.second)
                }
                Then("Population should be expected") {
                    assertEquals(testCase.value, populationResult)
                }
            }
        }
    }
})

fun initialData(): String {
    return "3,4,3,1,2"
}

fun adventData(): String {
    return "5,1,1,5,4,2,1,2,1,2,2,1,1,1,4,2,2,4,1,1,1,1,1,4,1,1,1,1,1,5,3,1,4,1,1,1,1,1,4,1,5,1,1,1,4,1,2,2,3,1,5,1,1,5,1,1,5,4,1,1,1,4,3,1,1,1,3,1,5,5,1,1,1,1,5,3,2,1,2,3,1,5,1,1,4,1,1,2,1,5,1,1,1,1,5,4,5,1,3,1,3,3,5,5,1,3,1,5,3,1,1,4,2,3,3,1,2,4,1,1,1,1,1,1,1,2,1,1,4,1,3,2,5,2,1,1,1,4,2,1,1,1,4,2,4,1,1,1,1,4,1,3,5,5,1,2,1,3,1,1,4,1,1,1,1,2,1,1,4,2,3,1,1,1,1,1,1,1,4,5,1,1,3,1,1,2,1,1,1,5,1,1,1,1,1,3,2,1,2,4,5,1,5,4,1,1,3,1,1,5,5,1,3,1,1,1,1,4,4,2,1,2,1,1,5,1,1,4,5,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,4,2,1,1,1,2,5,1,4,1,1,1,4,1,1,5,4,4,3,1,1,4,5,1,1,3,5,3,1,2,5,3,4,1,3,5,4,1,3,1,5,1,4,1,1,4,2,1,1,1,3,2,1,1,4"
}
