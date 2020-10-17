package com.alancamargo.weapons.ui.activities.robots

import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import br.com.concretesolutions.kappuccino.assertions.VisibilityAssertions.displayed
import com.alancamargo.weapons.R
import com.alancamargo.weapons.ui.activities.WeaponDetailsActivity
import com.alancamargo.weapons.ui.entities.UiWeapon
import com.alancamargo.weapons.ui.entities.UiWeaponType
import com.alancamargo.weapons.ui.tools.AdLoader
import io.mockk.verify
import org.koin.test.KoinTest
import org.koin.test.get

fun weaponDetailsActivity(
    block: WeaponDetailsActivityRobot.() -> Unit
) = WeaponDetailsActivityRobot().apply {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val weapon = UiWeapon(
        id = 1,
        name = "Short Magazine Lee-Enfield No. 1 Mk. III",
        year = null,
        manufacturer = null,
        country = null,
        type = UiWeaponType(1, "Bolt action rifle"),
        lengthInMm = null,
        massInKg = null,
        calibre = null,
        capacityInRounds = null,
        rateOfFireInRpm = null,
        effectiveRangeInM = null,
        photos = emptyList()
    )
    val intent = WeaponDetailsActivity.getIntent(context, weapon)

    ActivityScenario.launch<WeaponDetailsActivity>(intent)

    block()
}

class WeaponDetailsActivityRobot {

    infix fun assert(assertion: WeaponDetailsActivityAssertions.() -> Unit) {
        WeaponDetailsActivityAssertions().run(assertion)
    }

}

class WeaponDetailsActivityAssertions : KoinTest {

    fun weaponNameIsCorrect() {
        displayed {
            allOf {
                id(R.id.txtName)
                text("Short Magazine Lee-Enfield No. 1 Mk. III")
            }
        }
    }

    fun weaponTypeIsCorrect() {
        displayed {
            allOf {
                id(R.id.txtType)
                text("Type: Bolt action rifle")
            }
        }
    }

    fun adsAreLoaded() {
        verify {
            get<AdLoader>().loadAds(any())
        }
    }

}