package ru.hse.spb

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Tests {
    @Test
    fun testIsWord() {
        assertTrue(PetrLangUtils.ifWord("petr"))
        assertFalse(PetrLangUtils.ifWord("animatis"))
    }

    @Test
    fun testGenderMethod() {
        assertTrue(PetrLangUtils.ifMale("petr"))
        assertFalse(PetrLangUtils.ifMale("nataliala"))
    }

    @Test
    fun testPartOfTheSpeechMethod() {
        assertTrue(PetrLangUtils.ifNoun("petr"))
        assertFalse(PetrLangUtils.ifNoun("lios"))

        assertTrue(PetrLangUtils.ifAdj("nataliala"))
        assertFalse(PetrLangUtils.ifAdj("petr"))

        assertTrue(PetrLangUtils.ifVerb("feinites"))
        assertFalse(PetrLangUtils.ifVerb("vetra"))
    }

    @Test
    fun testCurState() {
        var checker = PetrLangChecker("nataliala kataliala vetra feinites")
        checker.readAdjs()
        checker.readNouns()

        assertEquals(0, checker.maleNumber)
        assertEquals(3, checker.femaleNumber)
        assertTrue(checker.isOneNoun)
        assertEquals(3, checker.curPos)
    }

    @Test
    fun testFinalStateMethod() {
        var checker = PetrLangChecker("nataliala kataliala vetra vetra feinites")
        checker.readAdjs()
        checker.readNouns()
        checker.readVerbs()

        assertFalse(checker.checkFinalState())
    }
}