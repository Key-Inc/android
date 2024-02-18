package com.keyinc.keymono.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.keyinc.keymono.presentation.ui.theme.Accent
import com.keyinc.keymono.presentation.ui.theme.FontSmall
import com.keyinc.keymono.presentation.ui.theme.InterLabelBold
import com.keyinc.keymono.presentation.ui.theme.LightBlue
import com.keyinc.keymono.presentation.ui.theme.PaddingMedium
import com.keyinc.keymono.presentation.ui.theme.PaddingSmall
import com.keyinc.keymono.presentation.ui.theme.PaddingTiny

@Composable
fun PhoneTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String,
    label: String,
    phoneNumberMask: String = "+7(000)000-00-00",
    maskDigitCharacter: Char = '0',
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {

    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = PaddingSmall, bottom = PaddingTiny),
        style = InterLabelBold,
        fontSize = FontSmall,
        color = Color.Black
    )
    BasicTextField(
        modifier = modifier
            .background(
                color = LightBlue,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .border(
                width = 3.dp,
                color = Accent,
                shape = RoundedCornerShape(PaddingSmall)
            )
            .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = { input ->
            val maxLength = phoneNumberMask.count { it == maskDigitCharacter }
            val trimmedInput = input.take(maxLength)
            onValueChange(trimmedInput)
        },
        textStyle = InterLabelBold,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        visualTransformation = PhoneNumberVisualTransformer(
            phoneNumberMask = phoneNumberMask,
            maskDigitCharacter = maskDigitCharacter
        ),
        enabled = true,
        cursorBrush = SolidColor(Color.Black)
    ) { innerTextField ->
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(PaddingMedium),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            innerTextField()
        }
    }
}


class PhoneNumberVisualTransformer(
    private val phoneNumberMask: String,
    private val maskDigitCharacter: Char
) : VisualTransformation {

    private val maximumLength = phoneNumberMask.count { it == maskDigitCharacter }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = if (text.length > maximumLength) text.take(maximumLength) else text

        val transformedText = buildAnnotatedString {
            if (trimmedText.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmedText.length && maskIndex < phoneNumberMask.length) {
                if (phoneNumberMask[maskIndex] != maskDigitCharacter) {
                    val nextDigitIndex = phoneNumberMask.indexOf(maskDigitCharacter, maskIndex)
                    append(phoneNumberMask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmedText[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(
            transformedText,
            PhoneNumberOffsetMapper(phoneNumberMask, maskDigitCharacter)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneNumberVisualTransformer) return false
        if (phoneNumberMask != other.phoneNumberMask) return false
        return maskDigitCharacter == other.maskDigitCharacter
    }

    override fun hashCode(): Int {
        return phoneNumberMask.hashCode()
    }
}

private class PhoneNumberOffsetMapper(val phoneNumberMask: String, val digitCharacter: Char) :
    OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var nonDigitCount = 0
        var index = 0
        while (index < offset + nonDigitCount) {
            if (phoneNumberMask[index++] != digitCharacter) nonDigitCount++
        }
        return offset + nonDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int =
        offset - phoneNumberMask.take(offset).count { it != digitCharacter }
}


