<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pageTitle" value="Welcome to myCalendar!" scope="request" />
<jsp:include page="./includes/header.jsp" />
<p>Below you can find some highlights about myCalendar. Each chapter
	will have a slightly different summary depending on what will be done.</p>
<ul>
	<li><a id="eventsLink" href="events/">All Events</a> - shows all
		events for all users. Once secured, this will only be viewable by
		administrators.</li>
	<li><a id="myEventsLink" href="events/my">My Events</a> - shows
		all events that user1@example.com is the owner or attendee. Once we
		apply security only a logged in user will be able to view this page.
		Additionally, the logged in user will be used rather than always using
		user1@example.com</li>
	<li><a id="createEventLink" href="events/form">Create Event</a> -
		will allow creating a new Event with user1@example.com as the owner.
		Once we add security, only a logged in user will be able to view this
		page. Additionally, the logged in user will be the owner rather than
		always using user1@example.com.</li>

</ul>
<jsp:include page="./includes/footer.jsp" />