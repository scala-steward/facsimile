/*
Facsimile: A Discrete-Event Simulation Library
Copyright © 2004-2025, Michael J Allen.

This file is part of Facsimile.

Facsimile is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
version.

Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along with Facsimile. If not, see
http://www.gnu.org/licenses/lgpl.

The developers welcome all comments, suggestions and offers of assistance. For further information, please visit the
project home page at:

  http://facsim.org/

Thank you for your interest in the Facsimile project!

IMPORTANT NOTE: All patches (modifications to existing files and/or the addition of new files) submitted for inclusion
as part of the official Facsimile code base, must comply with the published Facsimile Coding Standards. If your code
fails to comply with the standard, then your patches will be rejected. For further information, please visit the coding
standards at:

  http://facsim.org/Documentation/CodingStandards/
========================================================================================================================
Scala source file from the org.facsim.anim.cell package.
*/

package org.facsim.anim.cell

import org.facsim.LibResource
import org.facsim.anim.Mesh
import org.facsim.anim.Point3D

/**
Class representing _[[http://www.automod.com/ AutoMod®]] cell trapezoid and
box_ primitives.

@note The use by _AutoMod_ of the term _trapezoid_ is a misnomer. These
shapes are more correctly and generally described as
_[[http://en.wikipedia.org/wiki/Frustum rectangular frusta]]_. More specific
shapes that can be represented by this cell include _boxes_, _cubes_,
_cuboids_ and _square frusta_. A _[[http://en.wikipedia.org/wiki/Trapezoid
trapezoid]]_ is the name for a 2D quadrilateral with two parallel sides.
However, the term _trapezoid_ is used throughout this file since it will be
familiar to users of _AutoMod_ & _cell files_.

@see
[[http://facsim.org/Documentation/Resources/AutoModCellFile/Trapezoids.html
Trapezoids & Boxes]] for further information.

@constructor Construct a new trapezoid primitive from the data stream.

@param scene Reference to the CellScene of which this cell is a part.

@param parent Parent set of this cell primitive. If this value is `None`, then
this cell is the scene's root cell.

@throws org.facsim.anim.cell.IncorrectFormatException if the file supplied is
not an _AutoMod® cell_ file.

@throws org.facsim.anim.cell.ParsingErrorException if errors are encountered
during parsing of the file.

@see
[[http://facsim.org/Documentation/Resources/AutoModCellFile/Trapezoids.html
Trapezoids & Boxes]] for further information.
*/

private[cell] final class Trapezoid(scene: CellScene, parent: Option[Set])
extends Mesh3D(scene, parent) {

/**
Trapezoid base Y-axis dimension.

Base of the trapezoid measured along the Y-Axis.

@note _ACE_ (the _AutoMod Creation Editor_) terms this the _base length_,
although most people would refer to it as the _base depth_ or _base width_.
Here, we refer to it as the base Y-axis dimension to avoid confusion.
*/

  private val baseYDim = scene.readDouble(_ >= 0.0,
  LibResource(Trapezoid.ReadDimKey, 1))

/**
Trapezoid base X-axis dimension.

Base of the trapezoid measured along the X-Axis.

@note _ACE_ (the _AutoMod Creation Editor_) terms this the _base width_,
although most people would refer to it as the _base length_. Here, we refer
to it as the base X-axis dimension to avoid confusion.
*/

  private val baseXDim = scene.readDouble(_ >= 0.0,
  LibResource(Trapezoid.ReadDimKey, 0))

/**
Trapezoid top Y-axis dimension.

Top of the trapezoid measured along the Y-Axis.

@note _ACE_ (the _AutoMod Creation Editor_) terms this the _top length_,
although most people would refer to it as the _top depth_ or _top width_.
Here, we refer to it as the top Y-axis dimension to avoid confusion.
*/

  private val topYDim = scene.readDouble(_ >= 0.0,
  LibResource(Trapezoid.ReadDimKey, 3))

/**
Trapezoid top X-axis dimension.

Top of the trapezoid measured along the X-Axis.

@note _ACE_ (the _AutoMod Creation Editor_) terms this the _top width_,
although most people would refer to it as the _top length_. Here, we refer
to it as the base X-axis dimension to avoid confusion.
*/

  private val topXDim = scene.readDouble(_ >= 0.0,
  LibResource(Trapezoid.ReadDimKey, 2))

/**
Trapezoid height (Z-dimension).

Height of the trapezoid measured along the Z-Axis.
*/

  private val height = scene.readDouble(_ >= 0.0,
  LibResource(Trapezoid.ReadDimKey, 4))

/**
Trapezoid top X-axis offset.
*/

  private val xOffset = scene.readDouble(LibResource
 (Trapezoid.ReadOffsetKey, 0))

/**
Trapezoid top Y-axis offset.
*/

  private val yOffset = scene.readDouble(LibResource
 (Trapezoid.ReadOffsetKey, 1))

/**
@inheritdoc

@note The origin of the trapezoid is at the center of its base.
*/
  protected[cell] override def cellMesh: Mesh =
  Mesh.rectangularFrustum(Point3D.Origin, baseXDim, baseYDim,
  Point3D(xOffset, yOffset, height), topXDim, topYDim)
}

/**
Trapezoid companion object.
*/

private object Trapezoid {

/**
Read dimension string resource key.
*/

  val ReadDimKey = "anim.cell.Trapezoid.readDim"

/**
Read offset string resource key.
*/

  val ReadOffsetKey = "anim.cell.Trapezoid.readOffset"
}