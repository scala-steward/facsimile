/*
Facsimile -- A Discrete-Event Simulation Library
Copyright © 2004-2013, Michael J Allen.

This file is part of Facsimile.

Facsimile is free software: you can redistribute it and/or modify it under the
terms of the GNU Lesser General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option) any
later version.

Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
details.

You should have received a copy of the GNU Lesser General Public License along
with Facsimile.  If not, see http://www.gnu.org/licenses/lgpl.

The developers welcome all comments, suggestions and offers of assistance.  For
further information, please visit the project home page at:

  http://facsim.org/

Thank you for your interest in the Facsimile project!

IMPORTANT NOTE: All patches (modifications to existing files and/or the
addition of new files) submitted for inclusion as part of the official
Facsimile code base, must comply with the published Facsimile Coding Standards.
If your code fails to comply with the standard, then your patches will be
rejected.  For further information, please visit the coding standards at:

  http://facsim.org/Documentation/CodingStandards/
===============================================================================
Scala source file from the org.facsim.test package.
*/
//=============================================================================

package org.facsim.test

import org.facsim.LibResource
import org.scalatest.FunSpec

//=============================================================================
/**
Miscellaneous functions for supporting ''Facsimile'' testing.
*/
//=============================================================================

trait CommonTestMethods {
  this: FunSpec =>

//-----------------------------------------------------------------------------
/**
Assert that a NullPointerException's message resulting from a
[[org.facsim.requireNonNull(AnyRef)*]] failure matches the expected value.

@param e Exception thrown.

@param argName Name of the argument that was found to be `null`.
*/
//-----------------------------------------------------------------------------

  final def assertRequireNonNullMsg (e: NullPointerException, argName: String):
  Unit = {
    println ("DEBUG: e.getMessage () is: " + e.getMessage ())
    println ("DEBUG: LibResource (\"requireNonNull\", \"" + argName +
    "\") is: " + LibResource.apply ("requireNonNull", argName))
    assert (e.getMessage () === LibResource ("requireNonNull", argName))
  }

//-----------------------------------------------------------------------------
/**
Assert that an IllegalArgumentException's message resulting from a
[[org.facsim.requireValid(Any,Boolean)*]] failure matches the expected value.

@param e Exception thrown.

@param argName Name of the argument that was found to be invalid.

@param argValue Invalid argument value.
*/
//-----------------------------------------------------------------------------

  final def assertRequireValidMsg (e: IllegalArgumentException, argName:
  String, argValue: Any): Unit = assert (e.getMessage () === LibResource
  ("requireValid", argName, argValue.toString))

//-----------------------------------------------------------------------------
/**
Assert that an IllegalArgumentException's message resulting from a
[[org.facsim.requireFinite(Double)*]] failure matches the expected value.

@param e Exception thrown.

@param argName Name of the argument that was found to be not finite.

@param argValue Invalid argument value.
*/
//-----------------------------------------------------------------------------

  final def assertRequireFiniteMsg (e: IllegalArgumentException, argName:
  String, argValue: Double): Unit = {
    val badValueCode = argValue match {
      case x if x.isNaN => 0
      case Double.PositiveInfinity => 1
      case Double.NegativeInfinity => 2
      case _ => throw new AssertionError ("Failed value IS finite!")
    }
    assert (e.getMessage () === LibResource ("requireFinite", argName,
    badValueCode))
  }
}