import React from "react";
import { PageLayout } from "../components/page-layout";

const BookingConfirmationPage = () => {
  return (
    <PageLayout>
      <div className="container">
        <h1>Booking successful</h1>
          <a href={"/bookings"}><h5>See my bookings</h5> </a>
      </div>
    </PageLayout>
  );
};

export default BookingConfirmationPage;
