package com.alancamargo.weapons.ui.activities.robots

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import br.com.concretesolutions.kappuccino.custom.recyclerView.RecyclerViewInteractions.recyclerView
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.activities.WeaponListActivity
import com.alancamargo.weapons.ui.navigation.WeaponDetailsActivityNavigation
import com.alancamargo.weapons.ui.queries.WeaponQuery
import io.mockk.verify
import org.koin.test.KoinTest
import org.koin.test.get

fun weaponListActivity(block: WeaponListActivityRobot.() -> Unit): WeaponListActivityRobot {
    return WeaponListActivityRobot().apply {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = WeaponListActivity.getIntent(context, WeaponQuery.All)

        ActivityScenario.launch<WeaponListActivity>(intent)

        block()
    }
}

class WeaponListActivityRobot {

    infix fun clickWeapon(assertion: WeaponListActivityAssertions.() -> Unit) {
        recyclerView(R.id.recyclerView) {
            atPosition(0) {
                click()
            }
        }

        assert(assertion)
    }

    infix fun assert(assertion: WeaponListActivityAssertions.() -> Unit) {
        WeaponListActivityAssertions().run(assertion)
    }

}

class WeaponListActivityAssertions : KoinTest {

    fun numberOfWeaponsIs(number: Int) {
        recyclerView(R.id.recyclerView) {
            sizeIs(number)
        }
    }

    fun weaponDetailsActivityIsLaunched() {
        verify {
            get<WeaponDetailsActivityNavigation>().startActivity(any(), any())
        }
    }

}