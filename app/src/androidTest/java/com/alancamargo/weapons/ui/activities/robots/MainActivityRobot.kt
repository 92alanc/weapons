package com.alancamargo.weapons.ui.activities.robots

import androidx.test.core.app.ActivityScenario
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.activities.MainActivity
import com.alancamargo.weapons.ui.navigation.WeaponListActivityNavigation
import io.mockk.verify
import org.koin.test.KoinTest
import org.koin.test.get

fun mainActivity(block: MainActivityRobot.() -> Unit) = MainActivityRobot().apply {
    ActivityScenario.launch(MainActivity::class.java)
    block()
}

class MainActivityRobot {

    infix fun clickAllWeapons(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(0) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByName(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(1) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByCountry(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(2) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByYear(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(3) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByType(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(4) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByCalibre(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(5) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun clickByManufacturer(assertion: MainActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(6) {
                click()
            }
        }

        assert(assertion)
    }

    private infix fun assert(block: MainActivityAssertions.() -> Unit) {
        MainActivityAssertions().run(block)
    }

}

class MainActivityAssertions : KoinTest {

    fun weaponListActivityIsLaunched() {
        verify {
            get<WeaponListActivityNavigation>().startActivity(any(), any())
        }
    }

    fun nameSearchDialogueIsDisplayed() {
        displayed {
            text(R.string.find_weapons_by_name)
        }
    }

}