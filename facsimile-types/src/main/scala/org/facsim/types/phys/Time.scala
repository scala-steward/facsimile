//======================================================================================================================
// Facsimile: A Discrete-Event Simulation Library
// Copyright © 2004-2020, Michael J Allen.
//
// This file is part of Facsimile.
//
// Facsimile is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
// version.
//
// Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// details.
//
// You should have received a copy of the GNU Lesser General Public License along with Facsimile. If not, see:
//
//   http://www.gnu.org/licenses/lgpl.
//
// The developers welcome all comments, suggestions and offers of assistance. For further information, please visit the
// project home page at:
//
//   http://facsim.org/
//
// Thank you for your interest in the Facsimile project!
//
// IMPORTANT NOTE: All patches (modifications to existing files and/or the addition of new files) submitted for
// inclusion as part of the official Facsimile code base, must comply with the published Facsimile Coding Standards. If
// your code fails to comply with the standard, then your patches will be rejected. For further information, please
// visit the coding standards at:
//
//   http://facsim.org/Documentation/CodingStandards/
//======================================================================================================================

//======================================================================================================================
// Scala source file belonging to the org.facsim.types.phys types.
//======================================================================================================================
package org.facsim.types.phys

import org.facsim.types.LibResource

/** ''[[http://en.wikipedia.org/wiki/Time_in_physics Time]]'' physical quantity type.
 *
 *  All time values are stored internally in ''[[http://en.wikipedia.org/wiki/Second seconds]]'', which is the
 *  ''[[http://en.wikipedia.org/wiki/SI SI]]'' standard unit of types.
 *
 *  @see [[http://en.wikipedia.org/wiki/Time_in_physics Time]] on ''Wikipedia''.
 *
 *  @see [[http://en.wikipedia.org/wiki/Second Seconds]] on ''Wikipedia''.
 *
 *  @see [[http://en.wikipedia.org/wiki/SI International System of Units]] on ''Wikipedia''.
 *
 *  @since 0.0
 */
object Time
extends NonNegative {

  /** @inheritdoc */
  override type Measure = TimeMeasure

  /** @inheritdoc */
  override type Units = TimeUnits

  /** @inheritdoc */
  override val name: String = LibResource("phys.Time.name")

  /** Number of seconds exactly equivalent to one millisecond. */
  private val SecondsPerMS = 1.0 / 1000.0

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Millisecond milliseconds]]''.
   *
   *  @see [[http://en.wikipedia.org/wiki/Millisecond Milliseconds]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Milliseconds: Units = new Units(new LinearScaleConverter(SecondsPerMS), LibResource("phys.Time.Millisecond.sym"))

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Second seconds]]''.
   *
   *  @note ''Seconds'' are the ''[[http://en.wikipedia.org/wiki/SI SI]]'' standard units for time measurement, and the
   *  units that are used to store such measurements internally in ''Facsimile''.
   *
   *  In ''Facsimile'', a ''second'' is defined in accordance with ''SI'' standards.
   *
   *  @see [[http://en.wikipedia.org/wiki/Second Seconds]] on ''Wikipedia''.
   *
   *  @see [[http://en.wikipedia.org/wiki/SI International System of Units]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Seconds: Units = new Units(SIConverter, LibResource("phys.Time.Second.sym"))

  /** Number of seconds exactly equivalent to one minute. */
  private val SecondsPerMin = 60.0

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Minute minutes]]''.
   *
   *  @see [[http://en.wikipedia.org/wiki/Minute Minutes]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Minutes: Units = new Units(new LinearScaleConverter(SecondsPerMin), LibResource("phys.Time.Minute.sym"))

  /** Number of seconds exactly equivalent to one hour. */
  private val SecondsPerHour = SecondsPerMin * 60.0

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Hour hours]]''.
   *
   *  @see [[http://en.wikipedia.org/wiki/Hour Hours]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Hours: Units = new Units(new LinearScaleConverter(SecondsPerHour), LibResource("phys.Time.Hour.sym"))

  /** Number of seconds exactly equivalent to one day. */
  private val SecondsPerDay = SecondsPerHour * 24.0

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Day days]]''.
   *
   *  @see [[http://en.wikipedia.org/wiki/Day Days]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Days: Units = new Units(new LinearScaleConverter(SecondsPerDay), LibResource("phys.Time.Day.sym"))

  /** Number of seconds exactly equivalent to one week. */
  private val SecondsPerWeek = SecondsPerDay * 7.0

  /** Units for time measured in ''[[http://en.wikipedia.org/wiki/Week weeks]]''.
   *
   *  @note This is the largest unit of time currently supported by ''Facsimile''. Note that months, years, decades,
   *  centuries, millenia, etc. vary in duration depending upon a number of factors: leap seconds, leap years, days per
   *  month, etc. Consequently, there is no simple ''standard'' definition for higher units of time.
   *
   *  @see [[http://en.wikipedia.org/wiki/Week Weeks]] on ''Wikipedia''.
   *
   *  @since 0.0
   */
  val Weeks: Units = new Units(new LinearScaleConverter(SecondsPerWeek), LibResource("phys.Time.Week.sym"))

  /** Physical quantity family for time measurements. */
  protected[phys] override val family: Family = Family(timeExponent = 1)

  /** @inheritdoc */
  override val siUnits: Units = Seconds

  // Register this family.
  Family.register(family, Time)

  /** Time measurement factory function.
   *
   *  @param measure Measurement, in seconds, to be converted into a new types.
   *
   *  @return `types` in the form of a Time measurement.
   */
  private[phys] override def apply(measure: Double) = new Measure(measure)

  /** ''[[http://en.wikipedia.org/wiki/Time_in_physics Time]]'' measurement class.
   *
   *  Instances of this class represent ''time'' measurements.
   *
   *  @constructor Create new ''[[http://en.wikipedia.org/wiki/Time_in_physics time]]'' measurement value.
   *
   *  @param measure ''Time'' measurement expressed in ''[[Seconds]]''. This value must be finite and greater than or
   *  equal to zero.
   *
   *  @throws IllegalArgumentException if `types` is not finite or is negative.
   *
   *  @since 0.0
   */
  final class TimeMeasure private[phys](measure: Double)
  extends NonNegativeMeasure[TimeMeasure](measure)

  /** ''[[http://en.wikipedia.org/wiki/Time_in_physics Time]]'' unit of measurement family class.
   *
   *  Instances of this class represent units for expressing ''time'' measurements.
   *
   *  @constructor Create new ''[[http://en.wikipedia.org/wiki/Time_in_physics time]]'' unit of measurement.
   *
   *  @param converter Rules to be applied to convert a quantity measured in these units to and from the standard ''time
   *  [[http://en.wikipedia.org/wiki/SI SI]]'' units, ''seconds''.
   *
   *  @param symbol Symbol to be used when outputting measurement values expressed in these units.
   *
   *  @since 0.0
   */
  final class TimeUnits private[phys](converter: Converter, symbol: String)
  extends NonNegativeUnits(converter, symbol)
}