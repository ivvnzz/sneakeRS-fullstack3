Frontend integration and moderator view

What changed:
- The frontend now captures a token returned by POST /api/sales and displays it on the confirmation page.
- A new Moderator page exists at /admin/moderator that lists tokens from GET /api/sales/tokens (protected on the frontend by role check).

Quick start:
1. Run backend (from this project root):
   .\mvnw.cmd -DskipTests spring-boot:run
2. Run frontend (in the frontend folder):
   cd ../Sneakers-frontend-main
   npm install
   npm run dev

How it works:
- On checkout, frontend calls POST /api/sales. The backend returns the sale and a token string in the response.
- The frontend displays the token to the user and saves it to localStorage with key 'lastSaleToken'.
- Moderators can fetch tokens from /api/sales/tokens (for testing) or the recommended secure endpoint `/api/sales/tokens/secure` that requires an admin key header. The frontend checks the user role to show the route, and the secure endpoint requires the `X-ADMIN-KEY` header value to match the `ADMIN_KEY` environment variable configured on the backend.

Notes:
- For production deployments on Render, ensure the backend and frontend environment variable VITE_API_URL is set to the backend URL.
 - To use the secure tokens endpoint, set an ADMIN_KEY in the backend environment (on Render -> Environment Variables). For the frontend, set `VITE_ADMIN_KEY` with the same value in your `.env` or Vite environment on render.
- tokens.json is stored in the backend runtime directory; do not commit it.