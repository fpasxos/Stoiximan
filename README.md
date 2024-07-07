# Stoiximan Sports Events App

Welcome to the Stoiximan Sports Events App! This Android application displays a list of upcoming sports events, allowing users to filter and view events by sport type, mark them as favorites, and see a countdown timer for each event.

This README provides information on the project's setup, features, requirements, and more.

---

## Table of Contents

1. [Introduction](#introduction)
2. [Requirements](#requirements)
3. [Architecture](#architecture)
4. [UI Design](#ui-design)
5. [API Integration](#api-integration)
6. [Features](#features)
7. [Testing](#testing)

---

## Introduction

Stoiximan is a comprehensive live sport events application built with Kotlin, utilizing the
MVVM w/ multi-module architecture. The app integrates multiple modern technologies to ensure a
seamless user experience and robust functionality.

---

## Requirements

- Fetch event data from a mock API endpoint. Details about the API are provided in the API section below.
- Display events in a scrollable list, grouped by sport type, with competitors, countdown timers, and favorite buttons.
- Provide filtering options for events based on their favorite status.
- Real-time countdown updates.
- Display appropriate messages for no events or API fetch errors.
- Support for SDK21 and above.
- The app should work on both emulators and physical devices.

---

## Architecture

The application follows a modular architecture, leveraging a combination of feature-based and
component-based modularization. It mas use of MVVM, with layering of Data, Domain & Presentation.
* Events domain layer is empty for now, since all domain specific files have been moved to
core-domain in order to be accessible within the app.

### Core Components

- **Presentation Layer:**
    - **Design System:** Contains all the reusable design components.

- **Domain Layer:**
    - Contains business logic and is written in pure Kotlin for reusability across different
      applications.

- **Data Layer:**
    - Handles data fetching from remote and local sources. In this application, both remote and local sources are
      used.

- **Database Layer(core only):**
    - Handles data fetching from local store database, Room. In this application, the remote source is
      used.
  
### Modules

Each feature module comprises three layers: data, domain, and presentation. This separation ensures
clean structure to achieve separation of concerns and facilitates future scalability and
maintenance.

---

## UI Design

The UI is built using Jetpack Compose, ensuring a modern and responsive design.

### Main Screen

- **LiveEvents List:** Displays a list of all sport categories with their live events.
- **ExpandableSportEventCard:** Each Sport Category's card, which when click expands and shows the live sport events.
- **SportEventItem:** Individual Sport Live event, showing Home, Away, isFavourite and a remaining timer for the live event.
- **CountDownTimerItem:** A modular, separate count down item, indicating the time remaining for the respected event to start in HH:MM:SS.

- **Connectivity indicator at the top bar, indicating network connectivity changes

---

## API Integration

The app retrieves Sport Categories and SportEvents' data from the a Mock API, https://618d3aa7fe09aa001744060a.mockapi.io/api/sports.

### Data Fetching

- **Coroutine-based threading:** Ensures smooth data fetching and UI updates.
- **Ktor:** Used for making network requests.

### DI (Dependency Injection)

- **Koin:** Used for dependency injection.

### UI

- Compose is used for ui items
- Actions are user actions that are sent to the VM
- State is the current screen state and source of truth, which indicates the app's unique screen
  state at any time

---

## Features

### Show Sport Category's favourite-only Sport Evens

The user can filter each Sport Category with only its favourite Sport Events.

### Mark Specific Sport Event As Favourite

The user can select any sport event as favourite.

### Connectivity Changes

The app monitors network connectivity in real-time and notifies users of any changes.

### Utility Functions

A utility for handling string resources from ViewModel to UI without requiring context.

---

## Testing

The application includes unit tests for the business logic to ensure reliability and correctness.

---
