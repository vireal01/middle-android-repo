package com.example.androidpracticumcustomview.ui.theme

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
Задание:
Реализуйте необходимые компоненты;
Создайте проверку что дочерних элементов не более 2-х;
Предусмотрите обработку ошибок рендера дочерних элементов.
Задание по желанию:
Предусмотрите параметризацию длительности анимации.
 */
@Composable
fun CustomContainerCompose(
  modifier: Modifier,
  animationDuration: Int = 5000,
  firstChild: @Composable (() -> Unit)?,
  secondChild: @Composable (() -> Unit)?
) {
  AnimatedColumn(
    modifier = modifier
  ) {
    AnimatedItem(animationDuration = animationDuration) {
      firstChild?.invoke()
    }
    AnimatedItem(
      delayMillis = 2000,
      animationDirection = AnimationDirection.Down,
      animationDuration = animationDuration
    ) {
      secondChild?.invoke()
    }
  }
}

sealed class AnimationDirection {
  data object Up : AnimationDirection()
  data object Down : AnimationDirection()
}


@Composable
fun AnimatedColumn(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Box(modifier.fillMaxSize()) {
    Layout(
      content = content
    ) { measurables, constraints ->
      require(measurables.size <= 2) { "Cannot contain more than 2 elements" }

      val placeables = measurables.map { measurable ->
        measurable.measure(constraints)
      }

      val parentWidth = constraints.maxWidth
      val parentHeight = constraints.maxHeight

      layout(width = parentWidth, height = parentHeight) {

        placeables.forEach { placeable ->
          placeable.placeRelative(0, 0) // положение диктуется Box компонентом внутри AnimatedItem
          // не важно, какое дефолтное положение мы выберем внутри Layout
        }
      }
    }
  }
}

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun AnimatedItem(
  modifier: Modifier = Modifier,
  delayMillis: Int = 0,
  animationDirection: AnimationDirection = AnimationDirection.Up,
  animationDuration: Int = 5000,
  content: @Composable () -> Unit
) {
  val alpha = remember { Animatable(0f) }
  val offsetY = remember { Animatable(0f) }

  val offsetTarget = remember {
    when (animationDirection) {
      AnimationDirection.Up -> -200f
      AnimationDirection.Down -> 200f
    }
  }

  LaunchedEffect(Unit) {
    delay(delayMillis.toLong())
    launch { alpha.animateTo(1f, animationSpec = tween(2000)) }
    launch { offsetY.animateTo(offsetTarget, animationSpec = tween(animationDuration)) }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .alpha(alpha.value)
      .offset(y = offsetY.value.dp),
    contentAlignment = Alignment.Center,
  ) {
    content()
  }
}

