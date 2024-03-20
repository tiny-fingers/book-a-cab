import React from "react";
import { Route, Routes } from "react-router-dom";
import { HomePage } from "./pages/home-page";
import { NotFoundPage } from "./pages/not-found-page";
import { CallbackPage } from "./pages/callback-page";
import { PageLoader } from "./components/page-loader";
import { useAuth0 } from "@auth0/auth0-react";
import { AuthenticationGuard } from "./components/authentication-guard";
import BookingConfirmationPage from "./pages/booking-confirmation-page";
import BookingsPage from "./pages/bookings-page";

export const App = () => {
  const { isLoading } = useAuth0();

  if (isLoading) {
    return (
      <div className="page-layout">
        <PageLoader />
      </div>
    );
  }

  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route
        path="/booking-confirmation"
        element={<AuthenticationGuard component={BookingConfirmationPage} />}
      />
      <Route
        path="/bookings"
        element={<AuthenticationGuard component={BookingsPage} />}
      />
      <Route path="/callback" element={<CallbackPage />} />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
};
