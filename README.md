Sample Web Proxy portlet with Basic Authentication support
==========================================================

**WARNING**: This sample project was developed for demo purposes only.
This project wants to add Basic Authentication support to the Web Proxy portlet shipped with Liferay Portal and Based on [portletbridge](http://www.portletbridge.org/) project.

Project requirements
--------------------

* No use of EXT plugin
* Small code modifications
* Sample usage of `PortalDelegateServlet`

Runtime requirements
--------------------

In order to forward the user password to the 3rd-party web app you need to set `session.store.password=true` in your `portal-ext.properties` file.

TODO:
-----

* Configuration defaults are empty and may lead to parsing problems. Scope *MUST* be set.
* Bugs?
* Check [portletbridge 1.2](http://sourceforge.net/projects/portletbridge/?source=navbar) changes.