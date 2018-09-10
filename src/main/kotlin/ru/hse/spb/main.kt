package ru.hse.spb

import java.util.*

class PetrLangUtils {
    companion object {
        const val MALE_ADJ = "lios"
        const val MALE_NOUN = "etr"
        const val MALE_VERB = "initis"
        const val FEMALE_ADJ = "liala"
        const val FEMALE_NOUN = "etra"
        const val FEMALE_VERB = "inites"

        fun ifWord(word: String): Boolean {
            if (word.endsWith(MALE_ADJ) ||
                    word.endsWith(MALE_NOUN) ||
                    word.endsWith(MALE_VERB) ||
                    word.endsWith(FEMALE_ADJ) ||
                    word.endsWith(FEMALE_NOUN) ||
                    word.endsWith(FEMALE_VERB)) {
                return true
            }
            return false
        }

        fun ifMale(word: String): Boolean {
            if (word.endsWith(MALE_ADJ) ||
                    word.endsWith(MALE_NOUN) ||
                    word.endsWith(MALE_VERB)) {
                return true
            }
            return false
        }

        fun ifAdj(word: String): Boolean {
            if (word.endsWith(MALE_ADJ) ||
                    word.endsWith(FEMALE_ADJ)) {
                return true
            }
            return false
        }

        fun ifNoun(word: String): Boolean {
            if (word.endsWith(MALE_NOUN) ||
                    word.endsWith(FEMALE_NOUN)) {
                return true
            }
            return false
        }

        fun ifVerb(word: String): Boolean {
            if (word.endsWith(MALE_VERB) ||
                    word.endsWith(FEMALE_VERB)) {
                return true
            }
            return false
        }
    }
}

class PetrLangChecker(var text: String?) {
    var maleNumber = 0
    var femaleNumber = 0
    val words: List<String>
    var curPos = 0
    var isOneNoun = false

    init {
        words = text!!.split("\\s".toRegex())
    }

    fun noteGender(word: String) {
        if (PetrLangUtils.ifMale(word)) {
            maleNumber++
        } else {
            femaleNumber++
        }
    }

    fun readAdjs() {
        while (curPos < words.size && PetrLangUtils.ifAdj(words.get(curPos))) {
            noteGender(words.get(curPos))
            curPos++
        }
    }

    fun readNouns() {
        val startPos = curPos

        while (curPos < words.size && PetrLangUtils.ifNoun(words.get(curPos))) {
            noteGender(words.get(curPos))
            curPos++
        }

        isOneNoun = curPos - startPos == 1
    }

    fun readVerbs() {
        while (curPos < words.size && PetrLangUtils.ifVerb(words.get(curPos))) {
            noteGender(words.get(curPos))
            curPos++
        }
    }

    fun checkFinalState(): Boolean {
        if (curPos != words.size || (femaleNumber != 0 && maleNumber != 0) || isOneNoun.equals(false)) {
            if (words.size == 1 && PetrLangUtils.ifWord(words.get(0))) {
                return true
            }
            return false
        }
        return true
    }
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val text = input.nextLine()

    val checker = PetrLangChecker(text)
    checker.readAdjs()
    checker.readNouns()
    checker.readVerbs()

    if (checker.checkFinalState().equals(true)) {
        println("YES")
    } else {
        println("NO")
    }
}