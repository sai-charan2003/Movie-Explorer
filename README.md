# Movie Explorer

Movie Explorer is an Android application designed with Jetpack Compose, providing users with a smooth experience to explore a wide range of movies. Its user-friendly interface allows users to effortlessly navigate through movie listings and access detailed movie information.

The app fetches data from the api.themoviedb.org using Retrofit to ensure it provides users with the latest movie data.
 This data is then stored locally in a Room database, allowing users to access it even when offline. The use of paging enables the app to efficiently fetch more movies as users scroll through the listings, ensuring a seamless browsing experience.

To improve user experience, Movie Explorer utilizes WorkerManager to handle background tasks, such as fetching and storing movie data. This ensures that users can continue using the app smoothly without interruptions.

Additionally, the app follows the MVVM (Model-View-ViewModel) architecture, which helps in organizing the codebase and separating concerns, ultimately making it easier to maintain and update the app over time.

## Tech Stack used
- **MVVM architecture**: The app utilizes the robust MVVM architecture, contributing to its stability, maintainability, and future growth potential.
-  **Room Database**: All data is stored locally on your device using the reliable and efficient ROOM database, based on SQLite, ensuring your information remains secure and readily accessible.
-  **Worker Manager**: Workermanger is used to fetch the data from the api for every 30 minutes in the background.
- **Retrofit**: Employed Retrofit, a powerful HTTP client for Android and Java, to seamlessly handle network requests and interact with the api.themoviedb.org, ensuring efficient data retrieval and communication.
- **Google Material 3 design**: Built with Google's Material Design 3 principles, To-Do List boasts a sleek and intuitive interface that adapts dynamically to your wallpaper colors for a personalized touch.
- **Kotlin**: Utilized the powerful Jetpack Compose framework based on Kotlin to build this app."

## Project Structure
![Screenshot 2024-02-06 153051](https://github.com/sai-charan2003/Movie-Explorer/assets/83913880/a101a722-a306-47e3-a802-e58867c30825)


The project follows a modular structure with key components:



- **data/**: Classes related to data handling.
  - **localdatabase/**: Room database implementation.
  - **model/**: Data models used throughout the app.
  - **movie_details_data/**: Data models specifically related to movie details.
  - **movieslist/**: Data models for movie lists.

- **navigation/**: Files related to navigation within the app.
  - **Destination.kt**: Defines destinations within the app's navigation graph.
  - **navhost.kt**: Manages the navigation host setup.

- **screens/**: Jetpack Compose UI components for different screens in the app.
  - **homescreen.kt**: UI component for the home screen.
  - **settings.kt**: UI component for the settings screen.
  - **detailsscreen.kt**: UI component for the movie details screen.

- **ui/theme/**: Files related to the app's theme and styling.
  - **Color.kt**: Color definitions.
  - **Theme.kt**: Theme setup.
  - **Type.kt**: Typography definitions.

- **utils/**: Utility functions and helper classes used throughout the app.
  - **tasks.kt**: Task-related utility functions.
  - **loadmode.kt**: Functions related to loading modes.

- **viewmodel/**: ViewModel responsible for managing UI-related data and state.

- **workermanager/**: Files related to background tasks managed by WorkerManager.
  - **workermanager.kt**: Handles background tasks, such as fetching data from the API.


## Setup Process

You can download the app from the below link:

[Download Link](https://github.com/sai-charan2003/Movie-Explorer/releases/download/Movie-Explorer/app-release.apk).

or you can can clone the app and run on your own
### Prerequisites

- **Android Studio:** Ensure you have Android Studio installed. You can download it from [developer.android.com/studio](https://developer.android.com/studio).

### Installation Steps

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/sai-charan2003/Movie-Explorer.git
    ```


2. **Open Project in Android Studio:**
    - Launch Android Studio.
    - Select "Open an existing Android Studio project."
    - Navigate to the cloned `Movie Explorer` directory and select the `build.gradle` file.

3. **Build the Project:**
    Once the project is loaded, Android Studio might take some time to index and build the project.

4. **Run the Application:**
    - Connect an Android device or use an emulator.
    - Click on the green play button in Android Studio to run the app on your device/emulator.


## Screenshots
![Group 30](https://github.com/sai-charan2003/Movie-Explorer/assets/83913880/004e2cbe-869e-43b3-8433-898bb6786357)
![Group 31](https://github.com/sai-charan2003/Movie-Explorer/assets/83913880/518a5bd2-f9cd-4e07-a69b-155cf79fcc07)
![Group 32](https://github.com/sai-charan2003/Movie-Explorer/assets/83913880/21a5f3ba-703c-4508-86f0-101bf627e8ab)






