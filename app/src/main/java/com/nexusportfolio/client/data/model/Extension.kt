package com.nexusportfolio.client.data.model

import androidx.compose.ui.geometry.Offset
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow
import kotlin.math.sqrt

class Extension {
    companion object{
        fun Int.rupiahFormatting(): String {
            val localeID = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(this).replace("Rp", "Rp ")
        }

        /**
         * Find the distance between two points in a graph.
         * Calculated using the pythagorean theorem.
         */
        fun findTouchDistanceFromCenter(center: Offset, touch: Offset) =
            sqrt((touch.x - center.x).pow(2) + (touch.y - center.y).pow(2))

        /**
         * The touch point starts from Canvas top left which ranges
         * from (0,0) -> (canvas.width, canvas.height). We need to normalize this
         * point so that the (0,0) is located in the center of the graph instead.
         */
        fun Offset.findNormalizedPointFromTouch(canvasCenter: Offset) =
            Offset(this.x, canvasCenter.y + (canvasCenter.y - this.y))

        /**
         * Calculate the touch angle based on the canvas center. Then
         * adjust the angle so that drawing starts from the 4th quadrant
         * instead of the first.
         */
        fun calculateTouchAngleAccordingToCanvas(canvasCenter: Offset, normalizedPoint: Offset): Float {
            val angle = calculateTouchAngleInDegrees(canvasCenter, normalizedPoint)
            return adjustAngleToCanvas(angle).toFloat()
        }

        /**
         * Calculate touch angle in radian using atan2(). Afterwards, convert the
         * radian to degrees to be compared to arc data points.
         */
        fun calculateTouchAngleInDegrees(canvasCenter: Offset, normalizedPoint: Offset): Double {
            val touchInRadian = kotlin.math.atan2(normalizedPoint.y - canvasCenter.y,
                normalizedPoint.x - canvasCenter.x)
            return touchInRadian * -180 / Math.PI // Convert radians to angle in degrees
        }

        /**
         * Start from 4th quadrant going to 1st quadrant, degrees ranging from 0 to 360
         */
        fun adjustAngleToCanvas(angle: Double) = (angle + 360.0f) % 360.0f

        fun DrawingAngles.isInsideAngle(angle: Float) = angle > this.start && angle < this.start + this.end

        fun DonutChartDataCollection.calculateGap(gapPercentage: Float): Float{
            if(this.items.isEmpty()) return 0f

            return (this.totalAmount / this.items.size) * gapPercentage
        }

        fun DonutChartDataCollection.getTotalAmountWithGapIncluded(gapPercentage:Float): Float{
            val gap = this.calculateGap(gapPercentage)
            return this.totalAmount + (this.items.size * gap)
        }

        private fun DonutChartDataCollection.calculateGapAngle(gapPercentage:Float):Float{
            val gap = this.calculateGap(gapPercentage)
            val totalAmountWithGap = this.getTotalAmountWithGapIncluded(gapPercentage)

            return (gap / totalAmountWithGap) * 360f
        }

        private fun DonutChartDataCollection.findSweepAngle(
            index : Int,
            gapPercentage: Float
        ):Float {
            val percentage = items[index].percentage.toFloat()
            val gap = this.calculateGap(gapPercentage)
            val totalWithGap = getTotalAmountWithGapIncluded(gapPercentage)
            val gapAngle = this.calculateGapAngle(gapPercentage)
            return ((((percentage + gap) / totalWithGap) * 360f)) - gapAngle
        }
    }
}