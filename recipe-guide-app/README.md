# Tarif Rehberi Uygulaması

Bu proje, kullanıcıların tarifleri yönetebileceği, malzemeleri takip edebileceği ve tarif önerileri alabileceği bir Java uygulamasıdır. Uygulama, kullanıcıların tarifleri eklemesine, güncellemesine ve silmesine olanak tanırken, malzemelerin stok durumunu da takip eder. Ayrıca, kullanıcılar mevcut malzemelerine göre tarif önerileri alabilirler.

## Proje Yapısı

Proje, aşağıdaki ana bileşenlerden oluşmaktadır:

- **Entity**: Veri modellerini içerir (Recipe, Ingredient, RecipeIngredient). Bu modeller, tariflerin ve malzemelerin temel özelliklerini tanımlar.
- **DAO**: Veritabanı erişim katmanını içerir (RecipeDAO, IngredientDAO, RecipeIngredientDAO). Bu katman, veritabanı işlemlerini gerçekleştirir.
- **Service**: İş mantığı katmanını içerir (RecipeService, IngredientService, RecipeIngredientService). Bu katman, uygulamanın iş mantığını yönetir.
- **GUI**: Kullanıcı arayüzü bileşenlerini içerir (MainPage, RecipeDetail, RecipeSuggestion, vb.). Bu bileşenler, kullanıcıların uygulama ile etkileşimde bulunmasını sağlar.

## Özellikler

- Tarif ekleme, güncelleme ve silme
- Malzeme ekleme, güncelleme ve silme
- Tarif detaylarını görüntüleme
- Malzeme detaylarını görüntüleme
- Tarif önerileri alma
- Tarifleri hazırlama ve malzeme stoklarını güncelleme

## Teknolojiler

- **Java**: Uygulamanın temel programlama dili.
- **JavaFX**: Kullanıcı arayüzü oluşturmak için kullanılan bir kütüphane.
- **MySQL**: Veritabanı yönetimi için kullanılan bir ilişkisel veritabanı sistemi.
- **Maven**: Proje yönetimi ve bağımlılık yönetimi için kullanılan bir araç.

## Kurulum

1. Projeyi klonlayın.
2. MySQL veritabanını kurun ve gerekli tabloları oluşturun.
3. `pom.xml` dosyasındaki bağımlılıkları yükleyin.
4. Uygulamayı çalıştırın.

## Kullanım

Uygulama başlatıldığında ana sayfa açılır. Buradan tarifleri görüntüleyebilir, yeni tarifler ekleyebilir ve malzemeleri yönetebilirsiniz. Tarif önerileri almak için ilgili bölümü kullanabilirsiniz.



# Recipe Guide Application

This project is a Java application that allows users to manage recipes, track ingredients, and receive recipe suggestions. The application enables users to add, update, and delete recipes while also tracking ingredient inventory. Additionally, users can receive recipe suggestions based on their available ingredients.

## Project Structure

The project consists of the following main components:

- **Entity**: Contains data models (Recipe, Ingredient, RecipeIngredient). These models define the basic properties of recipes and ingredients.
- **DAO**: Contains the database access layer (RecipeDAO, IngredientDAO, RecipeIngredientDAO). This layer handles database operations.
- **Service**: Contains the business logic layer (RecipeService, IngredientService, RecipeIngredientService). This layer manages the application's business logic.
- **GUI**: Contains user interface components (MainPage, RecipeDetail, RecipeSuggestion, etc.). These components enable user interaction with the application.

## Features

- Add, update, and delete recipes
- Add, update, and delete ingredients
- View recipe details
- View ingredient details
- Get recipe suggestions
- Prepare recipes and update ingredient stocks

## Technologies

- **Java**: The core programming language of the application.
- **JavaFX**: A library used for creating the user interface.
- **MySQL**: A relational database system used for database management.
- **Maven**: A tool used for project management and dependency management.

## Installation

1. Clone the project.
2. Install MySQL database and create necessary tables.
3. Install dependencies from the `pom.xml` file.
4. Run the application.

## Usage

When the application starts, the main page opens. From here, you can view recipes, add new recipes, and manage ingredients. You can use the relevant section to get recipe suggestions. 
