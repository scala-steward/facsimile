/*
Facsimile -- A Discrete-Event Simulation Library
Copyright © 2004-2015, Michael J Allen.

This file is part of Facsimile.

Facsimile is free software: you can redistribute it and/or modify it under the
terms of the GNU Lesser General Public License as published by the Free
Software Foundation, either version 3 of the License, or (at your option) any
later version.

Facsimile is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.

You should have received a copy of the GNU Lesser General Public License along
with Facsimile. If not, see http://www.gnu.org/licenses/lgpl.

The developers welcome all comments, suggestions and offers of assistance. For
further information, please visit the project home page at:

  http://facsim.org/

Thank you for your interest in the Facsimile project!

IMPORTANT NOTE: All patches (modifications to existing files and/or the
addition of new files) submitted for inclusion as part of the official
Facsimile code base, must comply with the published Facsimile Coding Standards.
If your code fails to comply with the standard, then your patches will be
rejected. For further information, please visit the coding standards at:

  http://facsim.org/Documentation/CodingStandards/
===============================================================================
Scala source file from the org.facsim.frp package.
*/
//=============================================================================

package org.facsim

//=============================================================================
/**
''Functional Reactive Programming'' (''FRP'') package.

@note The elements in this package are ''Scala'' ports of the
''[[https://github.com/SodiumFRP/sodium Sodium FRP Java API]]'', which was
developed by Stephen Blackheath, Anthony Jones and others. While a true
''Scala'' port of this code, some refactoring has also taken place. In
particular, note that the elements in this package are unrelated to the
"official" ''Scala'' port of ''Sodium'', which was created by Mark Butler. In
addition, some class names have also been modified; for instance, the
''Sodium'' `Cell` class has here been renamed to [[org.facsim.frp.Property]],
while the ''Sodium'' `Stream` class is here called
[[org.facsim.frp.EventStream]]. Furthermore, additional simulation-related
features have been added to the API.
*/
//=============================================================================

package object frp